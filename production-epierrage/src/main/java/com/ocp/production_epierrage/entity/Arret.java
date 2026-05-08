package com.ocp.production_epierrage.entity;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "arrets")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public abstract class Arret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "date_debut")
    private LocalTime dateDebut;

    @Column(name = "date_fin")
    private LocalTime dateFin;

    private Double duree;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private TypeArret type;

    private String cause;

    private String equipement;

    @Column(columnDefinition = "text")
    private String description;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", length = 20)
    private StatutEnum statut = StatutEnum.BROUILLON;



    @PrePersist
    @PreUpdate
    public void calculerDuree() {
        if (dateDebut != null && dateFin != null) {
            long minutes = java.time.Duration.between(dateDebut, dateFin).toMinutes();
            if (minutes < 0) minutes += 1440;
            this.duree = Math.round(minutes * 100.0 / 60) / 100.0;
        }
    }

}