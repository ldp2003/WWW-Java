package iuh.edu.vn.backend.repositories;

import iuh.edu.vn.backend.ids.JobSkillId;
import iuh.edu.vn.backend.models.JobSkill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface JobSkillRepository extends CrudRepository<JobSkill, JobSkillId>, PagingAndSortingRepository<JobSkill, JobSkillId>{
}