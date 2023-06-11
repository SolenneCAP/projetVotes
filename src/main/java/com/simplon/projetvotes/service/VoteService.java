package com.simplon.projetvotes.service;

import com.simplon.projetvotes.model.Projet;
import com.simplon.projetvotes.model.Vote;
import com.simplon.projetvotes.repository.VoteRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Cette classe est un service pour gérer les opérations liées aux votes.
 */
@Data
@Service
public class VoteService {
    private final VoteRepository voteRepository;

    /**
     * Constructeur de la classe VoteService.
     *
     * @param voteRepository le référentiel de votes utilisé par le service
     */
    @Autowired
    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    /**
     * Recherche et renvoie une liste de votes correspondant à un projet et à un utilisateur spécifiés.
     *
     * @param projet le projet pour lequel on recherche les votes
     * @param user   l'utilisateur pour lequel on recherche les votes
     * @return une liste de votes correspondant aux critères de recherche
     */
    public List<Vote> findByProjetAndUser(Projet projet, String user) {
        return voteRepository.findByProjetAndUser(projet, user);
    }

    /**
     * Enregistre un vote.
     *
     * @param vote le vote à enregistrer
     */
    public void save(Vote vote) {
        voteRepository.save(vote);
    }
}

