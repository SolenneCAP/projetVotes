package com.simplon.projetvotes.repository;

import com.simplon.projetvotes.model.Projet;
import com.simplon.projetvotes.model.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Long> {
    @Query("SELECT v FROM Vote v WHERE v.projet = :projet AND v.user = :user")
    List<Vote> findByProjetAndUser(Projet projet, String user);
}
