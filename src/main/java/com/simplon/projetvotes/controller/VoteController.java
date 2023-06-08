package com.simplon.projetvotes.controller;


import com.simplon.projetvotes.EntityNotFoundException;
import com.simplon.projetvotes.model.Projet;
import com.simplon.projetvotes.model.Vote;
import com.simplon.projetvotes.repository.ProjetRepository;
import com.simplon.projetvotes.repository.VoteRepository;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class VoteController {
    private final ProjetRepository mRepository;
    private final VoteRepository mVoteRepository;

    @Autowired
    public VoteController(ProjetRepository pRepository, VoteRepository pVoteRepository) {
        mRepository = pRepository;
        mVoteRepository = pVoteRepository;
    }

    @SneakyThrows
    @GetMapping(path = "/votes/{projetIdProjet}")
    public String votesByProjet(@PathVariable Long projetIdProjet, Model model) {
        Optional<Projet> projetOptional = mRepository.findByProjetIdProjet(projetIdProjet);
        if (projetOptional.isPresent()) {
            Projet projet = projetOptional.get();
            model.addAttribute("projet", projet);
            model.addAttribute("votes", projet.getVotes());
        } else {
            throw new EntityNotFoundException("Le projet avec l'ID " + projetIdProjet + " n'existe pas.");
        }
        return "votes";
    }

    @PostMapping(path = "/votes/{projetIdProjet}")
    public String vote(
            @PathVariable Long projetIdProjet, @Valid @ModelAttribute Vote vote, BindingResult validation, Model model) {
        Projet projet = mRepository.getReferenceByIdProjet(projetIdProjet);
        if (projet != null) {
            model.addAttribute("projet", projet);
            model.addAttribute("vote", vote);
            model.addAttribute("votes", projet.getVotes());
            if (!validation.hasErrors()) {
                List<Vote> existingVotes = mVoteRepository.findByProjetAndUser(projet, vote.getUser());
                if (existingVotes.isEmpty()) {
                    vote.setProjet(projet);
                    vote.setVoteLe(LocalDateTime.now());
                    mVoteRepository.save(vote);
                    model.addAttribute("votes", projet.getVotes());
                } else {
                    String message = String.format("L'étudiant %s a déjà voté %d fois pour ce projet.",
                            vote.getUser(),
                            existingVotes.size());
                    model.addAttribute("alreadyVoted", message);
                }
            } else {
                model.addAttribute("errors", validation);
            }
        }
        return "votes";
    }
}
