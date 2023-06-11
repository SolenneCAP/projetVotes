package com.simplon.projetvotes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

/**
 * Cette classe représente un vote.
 */
@Entity
@Data
@Table(name = "votes")
public class Vote {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVote;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idProjet", referencedColumnName = "idProjet", nullable = false)
    @JsonIgnore
    private Projet projet;

    @NotNull
    @Column(nullable = false)
    private Boolean value;

    @Column(nullable = false)
    private LocalDateTime voteLe;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String user;

    /**
     * Constructeur de la classe Vote avec paramètres.
     *
     * @param pIdVote l'identifiant du vote
     * @param pProjet le projet associé au vote
     * @param pValue  la valeur du vote
     * @param pVoteLe la date et l'heure du vote
     * @param pUser   l'utilisateur ayant effectué le vote
     */
    public Vote(Long pIdVote, Projet pProjet, Boolean pValue, LocalDateTime pVoteLe, String pUser) {
        idVote = pIdVote;
        projet = pProjet;
        value = pValue;
        voteLe = pVoteLe;
        user = pUser;
    }

    /**
     * Constructeur par défaut de la classe Vote.
     */
    public Vote() {
    }

    /**
     * Obtient l'identifiant du vote.
     *
     * @return l'identifiant du vote
     */
    public Long getIdVote() {
        return idVote;
    }
}

