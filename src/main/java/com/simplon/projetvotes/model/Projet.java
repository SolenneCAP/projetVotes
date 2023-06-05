package com.simplon.projetvotes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.Future;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "projets")
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProjet")
    private Long id;

    @Column(name = "nomProjet")
    @Size(min = 5, max = 120)
    private String nomProjet;

    @Column(name = "descriptionProjet")
    private String descriptionProjet;

    //@Column(name = "creeLe")
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private LocalDateTime creeLe;

    @Future
    // @Column(nullable = false)
    @Column(name = "closLe")
    private LocalDateTime closLe;

    @NotNull
    @NotBlank
    @Column(name = "creePar")
    private String creePar;

}
