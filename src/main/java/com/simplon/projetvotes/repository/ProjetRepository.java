package com.simplon.projetvotes.repository;


import com.simplon.projetvotes.model.Projet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Cette interface définit les opérations de persistence pour l'entité Projet.
 */
@Repository
public interface ProjetRepository extends CrudRepository<Projet, Long> {
    /**
     * Récupère tous les projets en les triant par ordre de clôture.
     *
     * @param pPageable l'objet Pageable pour la pagination des résultats
     * @param now       la date et l'heure actuelles
     * @return une page de projets triés par ordre de clôture
     */
    @Query("SELECT s FROM Projet s ORDER BY CASE WHEN s.closLe > :now THEN 1 ELSE 2 END, s.closLe ASC")
    Page<Projet> findAllOrderByClosLe(Pageable pPageable, @Param("now") LocalDateTime now);

    /**
     * Obtient une référence au projet par son identifiant.
     *
     * @param projetIdProjet l'identifiant du projet
     * @return une référence au projet
     */
    Projet getReferenceByIdProjet(Long projetIdProjet);

    /**
     * Recherche un projet par son identifiant.
     *
     * @param projetId l'identifiant du projet
     * @return un objet Optional contenant le projet correspondant, s'il existe
     */
    @Query("SELECT p FROM Projet p WHERE p.id = :projetId")
    Optional<Projet> findByProjetIdProjet(@Param("projetId") Long projetId);
}

