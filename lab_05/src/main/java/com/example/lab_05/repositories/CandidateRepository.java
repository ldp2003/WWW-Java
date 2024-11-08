package com.example.lab_05.repositories;

import com.example.lab_05.models.Candidate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.logging.Logger;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
    List<Candidate> findAllByIdGreaterThan(int id, Pageable pageable);
}
