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

    // Attributs exacts du diagramme
    @Column(name = "numero_du_tas")
    private Integer numeroDuTas;

    @Column(name = "couche_ou_tranche")
    private String coucheOuTranche;

    private Double tonnage;

    @Column(name = "statut_couleur")
    private String statutCouleur;       // ROUGE, JAUNE, VERT, BLANC

    private String zone;                // A1, A2, B1, B2

    // Relation ManyToOne : Tas → TypeQualite (qualite_id FK, 0..* → 1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qualite_id")
    private TypeQualite qualite;

    // Relation inverse : Tas est référencé par EtatEpierrage (1..*  → 1)
    @OneToMany(mappedBy = "tas", fetch = FetchType.LAZY)
    private List<EtatEpierrage> etats;
}