package com.simplon.projetvotes.controller;

import com.simplon.projetvotes.model.Projet;
import com.simplon.projetvotes.repository.ProjetRepository;
import com.simplon.projetvotes.service.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController

public class ProjetController {
    @Autowired

    private ProjetService projetService;

    public ProjetController(ProjetRepository pRepository) {
    }

    /**
     * Création - Ajout d'un nouveau projet
     *
     * @param projet Un objet projet
     * @return L'objet projet est enregistré
     */
    @PostMapping("/projet")
    public Projet createProjet(@RequestBody Projet projet) {
        return projetService.saveProjet(projet);
    }

    /**
     * Lecture - Obtenir un projet
     *
     * @param idProjet L'identifiant du projet
     * @return Un objet projet complètement rempli
     */
    @GetMapping("/projet/{idProjet}")
    public Projet getProjet(@PathVariable final Long idProjet) {
        Optional<Projet> projet = projetService.getProjet(idProjet);
        return projet.orElse(null);
    }

    /**
     * Lecture - Obtenir tous les projets
     *
     * @return Un objet Iterable de Projet complètement rempli
     */
    @GetMapping("/projets")
    public Iterable<Projet> getProjets() {
        return projetService.getProjets();
    }

    /**
     * Mettre à jour - Mettre à jour un projet existant
     *
     * @param idProjet - L'identifiant du projet à mettre à jour
     * @param projet   - L'objet projet mis à jour
     * @return
     */
    @PutMapping("/projet/{idProjet}")
    public ResponseEntity<String> updateProjet(@PathVariable("idProjet") Long idProjet, @RequestBody Projet projet) {
        Optional<Projet> projetOptional = projetService.getProjet(idProjet);

        if (projetOptional.isPresent()) {
            Projet existingProjet = projetOptional.get();
            existingProjet.setNomProjet(projet.getNomProjet());
            existingProjet.setDescriptionProjet(projet.getDescriptionProjet());
            existingProjet.setCreeLe(projet.getCreeLe());
            existingProjet.setClosLe(projet.getClosLe());
            existingProjet.setCreePar(projet.getCreePar());


            Projet updatedProjet = projetService.saveProjet(existingProjet);

            return ResponseEntity.ok("Projet mis à jour avec succès");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Supprimer - Supprimer un projet
     *
     * @param idProjet - L'identifiant du projet à supprimer
     * @return
     */

    @DeleteMapping("/projet/{idProjet}")
    public ResponseEntity<String> deleteProjet(@PathVariable("idProjet") final Long idProjet) {
        projetService.deleteProjet(idProjet);
        return ResponseEntity.ok("Le projet a été supprimé avec succès");
    }
}

