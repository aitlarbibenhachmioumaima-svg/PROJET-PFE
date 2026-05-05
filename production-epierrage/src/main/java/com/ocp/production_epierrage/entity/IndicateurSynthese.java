package com.ocp.production_epierrage.entity;



import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "indicateurs_synthese")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class IndicateurSynthese {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Attributs exacts du diagramme (table IndicateurSynthese)
    @Column(unique = true, nullable = false)
    private String libelle;    // THE, THC, HM, Rendement

    @Column(unique = true, nullable = false)
    private String code;       // THE, THC, HM, REND

    // Relation inverse : IndicateurSynthese référencé par SyntheseDetail (0..* → 1)
    @OneToMany(mappedBy = "indicateur", fetch = FetchType.LAZY)
    private List<SyntheseDetail> details;
}