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

@Repository
public interface ProjetRepository extends CrudRepository<Projet, Long> {
    @Query("SELECT s FROM Projet s ORDER BY CASE WHEN s.closLe > :now THEN 1 ELSE 2 END, s.closLe ASC")
    Page<Projet> findAllOrderByClosLe(Pageable pPageable, @Param("now") LocalDateTime now);

    Projet getReferenceByIdProjet(Long projetIdProjet);

    @Query("SELECT p FROM Projet p WHERE p.id = :projetId")
    Optional<Projet> findByProjetIdProjet(@Param("projetId") Long projetId);

}
