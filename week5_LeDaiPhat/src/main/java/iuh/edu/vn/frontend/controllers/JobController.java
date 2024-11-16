package iuh.edu.vn.frontend.controllers;

import iuh.edu.vn.backend.ids.JobSkillId;
import iuh.edu.vn.backend.models.*;
import iuh.edu.vn.backend.repositories.CompanyRepository;
import iuh.edu.vn.backend.repositories.JobRepository;
import iuh.edu.vn.backend.repositories.JobSkillRepository;
import iuh.edu.vn.backend.repositories.SkillRepository;
import iuh.edu.vn.backend.services.CandidateService;
import iuh.edu.vn.backend.services.CompanyService;
import iuh.edu.vn.backend.services.EmailService;
import iuh.edu.vn.backend.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class JobController {
    @Autowired
    private JobService jobService;
    @Autowired
    private JobRepository  jobRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private JobSkillRepository jobSkillRepository;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private EmailService emailService;

    @GetMapping("/jobs")
    public String showJobListPaging(Model model,
                                    @RequestParam("page") Optional<Integer> page,
                                    @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<Job> jobPage = jobService.findAllPaging(currentPage - 1, pageSize, "id", "asc");
        model.addAttribute("jobs", jobPage);
        int totalPages = jobPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "jobs/jobs";
    }

    @GetMapping("/seeJobDetail/{id}")
    public String seeJobDetail(Model model, @PathVariable("id") Long id,
                               @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Job job = jobRepository.findById(id).get();
        Page<Candidate> candidatePage = candidateService.findMatchingCandidatesPaging(job, currentPage - 1, pageSize);

        model.addAttribute("job", job);
        model.addAttribute("candidatesSuggesting", candidatePage);

        int totalPages = candidatePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "jobs/jobDetail";
    }

    @GetMapping("/addJob")
    public String showAddJobForm(Model model) {
        model.addAttribute("job", new Job());
        model.addAttribute("companies", companyRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
        return "jobs/addJob";
    }

    @PostMapping("/addJob")
    public String addJob(Job job) {
        job.getJobSkills().removeIf(jobSkill -> jobSkill.getSkill().getId() == null);
        jobRepository.save(job);
        for (JobSkill jobSkill : job.getJobSkills()) {
            if (jobSkill.getSkill() != null) {
                JobSkillId jobSkillId = new JobSkillId();
                jobSkillId.setJobId(job.getId());
                jobSkillId.setSkillId(jobSkill.getSkill().getId());
                jobSkill.setId(jobSkillId);
                jobSkill.setJob(job);
            }
        }
        jobSkillRepository.saveAll(job.getJobSkills());
        return "redirect:/jobs";
    }

    @PostMapping("/seeJobDetail/{jobId}/apply/{candidateId}")
    public String applyForJob(@PathVariable Long jobId, @PathVariable Long candidateId, Model model) {
        Candidate candidate = candidateService.findById(candidateId);
        Job job = jobService.findById(jobId);
        emailService.sendApplicationEmail(candidate, job);
        model.addAttribute("successMessage", "Email sent successfully!");

        return "redirect:/jobs";
    }
}
