package com.ocp.production_epierrage.entity;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "arrets")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public abstract class Arret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Attributs exacts de la table Arret dans le diagramme image 1
    @Column(name = "date_debut")
    private LocalDateTime dateDebut;     // datetime

    @Column(name = "date_fin")
    private LocalDateTime dateFin;       // datetime

    private Double duree;                // decimal (calculé)

    // Relation : type_id (FK) → TypeArret  (0..* → 1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private TypeArret type;

    private String cause;                // varchar

    private String equipement;           // varchar

    @Column(columnDefinition = "text")
    private String description;          // text

    // Relation : utilisateur_id (FK) → Utilisateur
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    // Relation : statut_id (FK) → Statut
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "statut_id")
    private Statut statut;

    // Calcul automatique de la durée
    @PrePersist
    @PreUpdate
    public void calculerDuree() {
        if (dateDebut != null && dateFin != null) {
            long minutes = java.time.Duration.between(dateDebut, dateFin).toMinutes();
            if (minutes < 0) minutes += 1440; // passage minuit
            this.duree = Math.round(minutes * 100.0 / 60) / 100.0;
        }
    }

}