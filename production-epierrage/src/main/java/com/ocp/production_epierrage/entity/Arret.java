package com.ocp.production_epierrage.entity;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "arrets")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public abstract class Arret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_locale")
    private LocalDate dateLocale;

    @Column(name = "date_debut")
    private LocalTime  heureDebut;

    @Column(name = "date_fin")
    private LocalTime  heureFin;

    private Double duree;

    private String opérateur;
    private Integer semaine;

    private Integer poste;

    private String chaine;

    private String commentaire;

    private String atelier;

    private String panne;

    private Boolean fréquence;
    private String type;

    private String equipement;

    @Column(columnDefinition = "text")
    private String description;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", length = 20)
    private Statut statut = Statut.BROUILLON;



    @PrePersist
    @PreUpdate
    public void calculerDuree() {
        if ( heureDebut != null &&  heureFin != null) {
            long minutes = java.time.Duration.between( heureDebut,  heureFin).toMinutes();
            if (minutes < 0) minutes += 1440;
            this.duree = Math.round(minutes * 100.0 / 60) / 100.0;
        }
    }

}