package com.ocp.production_epierrage.entity;



import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "type_arrets")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TypeArret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Attributs exacts du diagramme
    @Column(unique = true, nullable = false)
    private String libelle;     // Mécanique, Électrique, Exploitation, Koch...

    @Column(unique = true, nullable = false)
    private String code;        // MEC, ELEC, EXP, MAT, KOCH, PLANIF, EXT...

    // Relation inverse : TypeArret référencé par Arret (0..* → 1)
    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    private List<Arret> arrets;
}