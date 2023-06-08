package com.simplon.projetvotes.service;

import com.simplon.projetvotes.model.Projet;
import com.simplon.projetvotes.model.Vote;
import com.simplon.projetvotes.repository.VoteRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class VoteService {
    private final VoteRepository voteRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public List<Vote> findByProjetAndUser(Projet projet, String user) {
        return voteRepository.findByProjetAndUser(projet, user);
    }

    public void save(Vote vote) {
        voteRepository.save(vote);
    }
}

