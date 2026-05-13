package com.ocp.production_epierrage.entity;



import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "synthese_production")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SyntheseProduction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalDate dateLocale;



    private Double thePoste3;

    private Double thePoste1;

    private Double thePoste2;

    private Double theJournee;



    private Double thcPoste3;

    private Double thcPoste1;

    private Double thcPoste2;

    private Double thcJournee;


    private Double hmPoste3;

    private Double hmPoste1;

    private Double hmPoste2;

    private Double hmJournee;



    private Double rendementPoste3;

    private Double rendementPoste1;

    private Double rendementPoste2;

    private Double rendementJournee;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
}