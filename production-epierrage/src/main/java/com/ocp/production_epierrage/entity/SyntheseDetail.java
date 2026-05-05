package com.ocp.production_epierrage.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "synthese_details")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SyntheseDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Attributs exacts du diagramme (table SyntheseDetail)

    // Relation : synthese_id (FK) → SyntheseProduction
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "synthese_id", nullable = false)
    private SyntheseProduction synthese;

    private Integer poste;      // int (1, 2, 3 ou null = journée totale)

    // Relation : indicateur_id (FK) → IndicateurSynthese  (0..* → 1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "indicateur_id", nullable = false)
    private IndicateurSynthese indicateur;

    private Double valeur;      // decimal

    private String unite;       // varchar (T, h, %)
}