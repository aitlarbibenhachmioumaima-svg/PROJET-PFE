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




    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "synthese_id", nullable = false)
    private SyntheseProduction synthese;

    private Integer poste;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "indicateur_id", nullable = false)
    private IndicateurSynthese indicateur;

    private Double valeur;

    private String unite;
}