package com.simplon.projetvotes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.Future;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Cette classe représente un projet.
 */
@Data
@Entity
@Table(name = "projets")
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProjet")
    private Long idProjet;

    @Column(name = "nomProjet")
    @Size(min = 5, max = 120)
    private String nomProjet;

    @Column(name = "descriptionProjet")
    private String descriptionProjet;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private LocalDateTime creeLe;

    @Future
    @Column(name = "closLe")
    private LocalDateTime closLe;

    @NotNull
    @NotBlank
    @Column(name = "creePar")
    private String creePar;

    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vote> votes = new ArrayList<>();

    /**
     * Constructeur par défaut de la classe Projet.
     */
    public Projet() {
        super();
        this.creeLe = LocalDateTime.now();
    }

    /**
     * Constructeur de la classe Projet avec paramètres.
     *
     * @param pIdProjet          l'identifiant du projet
     * @param pNomProjet         le nom du projet
     * @param pDescriptionProjet la description du projet
     * @param pCreePar           la personne qui a créé le projet
     * @param pCreeLe            la date et l'heure de création du projet
     * @param pClosLe            la date et l'heure de clôture du projet
     */
    public Projet(
            Long pIdProjet,
            String pNomProjet,
            String pDescriptionProjet,
            String pCreePar,
            LocalDateTime pCreeLe,
            LocalDateTime pClosLe) {
        super();
        setIdProjet(pIdProjet);
        setNomProjet(pNomProjet);
        setDescriptionProjet(pDescriptionProjet);
        setCreeLe(pCreeLe);
        setClosLe(pClosLe);
        setCreePar(pCreePar);
    }

    /**
     * Définit l'identifiant du projet.
     *
     * @param pIdProjet l'identifiant du projet
     */
    public void setIdProjet(Long pIdProjet) {
        this.idProjet = pIdProjet;
    }

    /**
     * Obtient le nom du projet.
     *
     * @return le nom du projet
     */
    public String getNomProjet() {
        return nomProjet;
    }

    /**
     * Définit le nom du projet.
     *
     * @param pNomProjet le nom du projet
     */
    public void setNomProjet(String pNomProjet) {
        this.nomProjet = pNomProjet;
    }

    /**
     * Obtient la description du projet.
     *
     * @return la description du projet
     */
    public String getDescriptionProjet() {
        return descriptionProjet;
    }

    /**
     * Définit la description du projet.
     *
     * @param pDescriptionProjet la description du projet
     */
    public void setDescriptionProjet(String pDescriptionProjet) {
        this.descriptionProjet = pDescriptionProjet;
    }

    /**
     * Obtient la date et l'heure de création du projet.
     *
     * @return la date et l'heure de création du projet
     */
    public LocalDateTime getCreeLe() {
        return creeLe;
    }

    /**
     * Définit la date et l'heure de création du projet.
     *
     * @param pCreeLe la date et l'heure de création du projet
     */
    public void setCreeLe(LocalDateTime pCreeLe) {
        creeLe = pCreeLe;
        if (creeLe != null) {
            creeLe = creeLe.truncatedTo(ChronoUnit.SECONDS);
        }
    }

    /**
     * Obtient la date et l'heure de clôture du projet.
     *
     * @return la date et l'heure de clôture du projet
     */
    public LocalDateTime getClosLe() {
        return closLe;
    }

    /**
     * Définit la date et l'heure de clôture du projet.
     *
     * @param pClosLe la date et l'heure de clôture du projet
     */
    public void setClosLe(LocalDateTime pClosLe) {
        closLe = pClosLe;
        if (closLe != null) {
            closLe = closLe.truncatedTo(ChronoUnit.SECONDS);
        }
    }

    /**
     * Obtient la personne qui a créé le projet.
     *
     * @return la personne qui a créé le projet
     */
    public String getCreePar() {
        return creePar;
    }

    /**
     * Définit la personne qui a créé le projet.
     *
     * @param pCreePar la personne qui a créé le projet
     */
    public void setCreePar(String pCreePar) {
        this.creePar = pCreePar;
    }

    /**
     * Obtient la liste des votes associés au projet.
     *
     * @return la liste des votes associés au projet
     */
    public Collection<Vote> getVotes() {
        return votes;
    }
}

