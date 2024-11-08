package com.example.lab_05.services;

import com.example.lab_05.models.Candidate;
import com.example.lab_05.repositories.CandidateRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.sql.DataSource;
import java.util.List;

public class CandidateService {
    private final CandidateRepository candidateRepository;
    public CandidateService(CandidateRepository candidateRepository){
        this.candidateRepository = candidateRepository;
    }

    public List<Candidate> findAllByIdGreaterThan(int id, int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size);;
        return candidateRepository.findAllByIdGreaterThan(id, pageRequest);
    }
}
