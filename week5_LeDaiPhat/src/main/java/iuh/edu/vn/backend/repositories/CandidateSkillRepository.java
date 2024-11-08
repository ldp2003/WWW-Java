package iuh.edu.vn.backend.repositories;

import iuh.edu.vn.backend.ids.CandidateSkillId;
import iuh.edu.vn.backend.models.CandidateSkill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CandidateSkillRepository extends CrudRepository<CandidateSkill, CandidateSkillId>, PagingAndSortingRepository<CandidateSkill, CandidateSkillId>{
}