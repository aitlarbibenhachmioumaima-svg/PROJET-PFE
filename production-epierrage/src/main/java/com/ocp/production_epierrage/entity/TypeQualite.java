package com.ocp.production_epierrage.entity;



import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "type_qualites")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TypeQualite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Attributs exacts du diagramme
    @Column(unique = true, nullable = false)
    private String libelle;       // C2, PS, C5, EXP, BG10, PBG/TESS...

    @Column(name = "code_couleur", nullable = false)
    private String codeCouleur;   // ROUGE, JAUNE, VERT, BLANC

    // Relation inverse : TypeQualite est référencé par Tas (0..*) et Production
    @OneToMany(mappedBy = "qualite", fetch = FetchType.LAZY)
    private List<Tas> tas;
}
