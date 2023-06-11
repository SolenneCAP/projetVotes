package com.simplon.projetvotes.service;

import com.simplon.projetvotes.model.Projet;
import com.simplon.projetvotes.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Cette classe implémente les fonctionnalités de service pour l'entité Projet.
 */
@Service
public class ProjetService {
    @Autowired
    private ProjetRepository projetRepository;

    /**
     * Obtient un projet en fonction de son identifiant.
     *
     * @param id l'identifiant du projet
     * @return un objet Optional contenant le projet correspondant, s'il existe
     */
    public Optional<Projet> getProjet(final Long id) {
        return projetRepository.findById(id);
    }

    /**
     * Obtient tous les projets.
     *
     * @return un itérable contenant tous les projets
     */
    public Iterable<Projet> getProjets() {
        return projetRepository.findAll();
    }

    /**
     * Supprime un projet en fonction de son identifiant.
     *
     * @param id l'identifiant du projet à supprimer
     */
    public void deleteProjet(final Long id) {
        projetRepository.deleteById(id);
    }

    /**
     * Enregistre un projet.
     *
     * @param projet l'objet projet à enregistrer
     * @return l'objet projet enregistré
     */
    public Projet saveProjet(Projet projet) {
        return projetRepository.save(projet);
    }
}

