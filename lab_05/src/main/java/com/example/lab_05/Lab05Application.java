package com.example.lab_05;

import com.example.lab_05.models.Candidate;
import com.example.lab_05.repositories.CandidateRepository;
import com.example.lab_05.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class Lab05Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Lab05Application.class, args);
    }

    @Autowired
    private CandidateRepository repository;

    @Override
    public void run(String... args) throws Exception {
//        PageRequest pageRequest = PageRequest.of(0, 5);
//        Page<Candidate> page = repository.findAll(pageRequest);
//        List<Candidate> candidates = page.getContent();
//
//        candidates.forEach(System.out::println);
//
//        repository.findAllByIdGreaterThan(5, pageRequest).forEach(System.out::println);
        CandidateService candidateService = new CandidateService(repository);
        List<Candidate> candidates = candidateService.findAllByIdGreaterThan(5, 0, 2);
        candidates.forEach(System.out::println);
    }
}
