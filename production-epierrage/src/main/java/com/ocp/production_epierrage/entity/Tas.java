package com.ocp.production_epierrage.entity;



import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "tas")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Tas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "numero_du_tas")
    private Integer numeroDuTas;

    @Column(name = "couche_ou_tranche")
    private String coucheOuTranche;

    private Double tonnage;

    @Column(name = "statut_couleur")
    private String statutCouleur;

    private String zone;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qualite_id")
    private TypeQualite qualite;


    @OneToMany(mappedBy = "tas", fetch = FetchType.LAZY)
    private List<EtatEpierrage> etats;
}