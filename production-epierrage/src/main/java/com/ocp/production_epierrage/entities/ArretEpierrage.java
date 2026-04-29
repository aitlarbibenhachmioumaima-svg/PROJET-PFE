package com.ocp.production_epierrage.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data



public class ArretEpierrage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String semaine;
    private Integer poste;
    private Integer chaine;
    private String operateur;
    private String equipement;
    private String descriptionArret;
    private String commentaire;
    private LocalTime debut;
    private LocalTime fin;
    private Double duree;
    private String atelier;
    private String type;
    private String panne;
    private Boolean frequence;
    @Enumerated (EnumType.STRING)
    private String statut; // Utilise les valeurs de l'énumération <<statut>>

}