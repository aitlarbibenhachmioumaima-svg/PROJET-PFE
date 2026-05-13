package com.ocp.production_epierrage.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "arret_roue_pelle")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArretRouePelle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_locale")
    private LocalDate dateLocale;

    private String couche;
    private String emplacement;

    private Double thc;

    private String miseAJour;

    private String organe;

    private String descriptionArret;

    private String atelier;

    @Column(name = "date_debut")
    private LocalTime heureDebut;

    @Column(name = "date_fin")
    private LocalTime  heureFin;

    private Double duree;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

}