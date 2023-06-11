package com.simplon.projetvotes.controller;

import com.simplon.projetvotes.model.Projet;
import com.simplon.projetvotes.repository.ProjetRepository;
import com.simplon.projetvotes.service.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


/**
 * Cette classe est un contrôleur REST pour la gestion des opérations liées aux projets.
 */
@RestController
public class ProjetController {
    @Autowired
    private ProjetService projetService;

    /**
     * Constructeur de la classe ProjetController.
     *
     * @param pRepository le référentiel de projets utilisé par le contrôleur
     */
    public ProjetController(ProjetRepository pRepository) {
    }

    /**
     * Crée et ajoute un nouveau projet.
     *
     * @param projet l'objet projet à ajouter
     * @return l'objet projet enregistré
     */
    @PostMapping("/projet")
    public Projet createProjet(@RequestBody Projet projet) {
        return projetService.saveProjet(projet);
    }

    /**
     * Récupère un projet en fonction de son identifiant.
     *
     * @param idProjet l'identifiant du projet à récupérer
     * @return l'objet projet complètement rempli, ou null s'il n'existe pas
     */
    @GetMapping("/projet/{idProjet}")
    public Projet getProjet(@PathVariable final Long idProjet) {
        Optional<Projet> projet = projetService.getProjet(idProjet);
        return projet.orElse(null);
    }

    /**
     * Récupère tous les projets.
     *
     * @return un objet Iterable de Projet contenant tous les projets
     */
    @GetMapping("/projets")
    public Iterable<Projet> getProjets() {
        return projetService.getProjets();
    }

    /**
     * Met à jour un projet existant.
     *
     * @param idProjet l'identifiant du projet à mettre à jour
     * @param projet   l'objet projet mis à jour
     * @return une réponse indiquant si la mise à jour a réussi ou si le projet n'a pas été trouvé
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
     * Supprime un projet.
     *
     * @param idProjet l'identifiant du projet à supprimer
     * @return une réponse indiquant si la suppression a réussi ou si une erreur s'est produite
     */
    @DeleteMapping("/projet/{idProjet}")
    public ResponseEntity<String> deleteProjet(@PathVariable("idProjet") final Long idProjet) {
        try {
            projetService.deleteProjet(idProjet);
            return ResponseEntity.ok("Le projet a été supprimé avec succès");
        } catch (Exception e) {
            // Gérer l'exception ici
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de la suppression du projet");
        }
    }
}
