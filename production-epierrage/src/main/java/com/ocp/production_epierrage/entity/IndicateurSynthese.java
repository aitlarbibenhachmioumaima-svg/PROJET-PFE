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


    @Column(unique = true, nullable = false)
    private String libelle;

    @Column(unique = true, nullable = false)
    private String code;


    @OneToMany(mappedBy = "indicateur", fetch = FetchType.LAZY)
    private List<SyntheseDetail> details;
}