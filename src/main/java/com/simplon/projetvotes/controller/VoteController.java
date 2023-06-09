package com.simplon.projetvotes.controller;


import com.simplon.projetvotes.model.Projet;
import com.simplon.projetvotes.model.Vote;
import com.simplon.projetvotes.repository.ProjetRepository;
import com.simplon.projetvotes.repository.VoteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Collection;
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
    public ResponseEntity<Collection<Vote>> votesByProjet(@PathVariable Long projetIdProjet, Model model) {
        Optional<Projet> projetOptional = mRepository.findByProjetIdProjet(projetIdProjet);
        if (projetOptional.isPresent()) {
            Projet projet = projetOptional.get();
            model.addAttribute("projet", projet);
            model.addAttribute("votes", projet.getVotes());
            return ResponseEntity.ok(projet.getVotes());
        } else {
            throw new EntityNotFoundException("Le projet avec l'ID " + projetIdProjet + " n'existe pas.");
        }
    }

    @PostMapping(path = "/votes/{projetIdProjet}")
    public ResponseEntity<?> vote(
            @PathVariable Long projetIdProjet, @Valid @RequestBody Vote vote, BindingResult validation, Model model) {
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
                    return ResponseEntity.created(URI.create("/votes/" + vote.getIdVote())).build();
                } else {
                    String message = String.format("L'étudiant %s a déjà voté %d fois pour ce projet.",
                            vote.getUser(),
                            existingVotes.size());
                    model.addAttribute("alreadyVoted", message);
                    return ResponseEntity.ok(existingVotes.get(0));
                }
            } else {
                model.addAttribute("errors", validation);
                return ResponseEntity.badRequest().body(validation.getAllErrors());
            }
        }
        return ResponseEntity.badRequest().body(vote);
    }
}
