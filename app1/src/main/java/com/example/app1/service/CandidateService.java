package com.example.app1.service;

import com.example.app1.entities.Candidate;
import com.example.app1.entities.User;
import com.example.app1.repositories.CandidateRepository;
import com.example.app1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    public void save(Candidate candidate){
        candidateRepository.save(candidate);
    }

    public List<Candidate> getAllCandidate() {
        return candidateRepository.findAll();
    }

    public void deleteAll(){
        candidateRepository.deleteAll();
    };

    public Candidate winner() {
        List<Candidate> candidates = candidateRepository.findAll();


        Map<Candidate, Integer> voteCount = new HashMap<>();
        for (Candidate candidate : candidates) {
            voteCount.put(candidate, candidate.getUsers().size());
        }

        Candidate winner = Collections.max(voteCount.entrySet(), Map.Entry.comparingByValue()).getKey();


        for (Candidate candidate : candidates) {
            if (!candidate.equals(winner)) {
                candidateRepository.delete(candidate);
            }
        }
        return winner;
    }

    public void voteForCandidate(Long candidateId, Integer userId) {
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow(() -> new EntityNotFoundException("Candidate not found"));
        List<User> userList = candidate.getUsers();
        User user = new User();
        user.setId(userId);
        if(userList.contains(user)){

        }
        userList.add(user);
        candidate.setUsers(userList);
        candidateRepository.save(candidate);
    }

}
