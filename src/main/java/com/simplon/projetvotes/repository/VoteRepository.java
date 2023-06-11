package com.simplon.projetvotes.repository;

import com.simplon.projetvotes.model.Projet;
import com.simplon.projetvotes.model.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Cette interface définit les opérations de persistence pour l'entité Vote.
 */
@Repository
public interface VoteRepository extends CrudRepository<Vote, Long> {
    /**
     * Recherche les votes d'un projet effectués par un utilisateur spécifique.
     *
     * @param projet le projet concerné
     * @param user   l'utilisateur
     * @return une liste de votes correspondant au projet et à l'utilisateur
     */
    @Query("SELECT v FROM Vote v WHERE v.projet = :projet AND v.user = :user")
    List<Vote> findByProjetAndUser(Projet projet, String user);
}
