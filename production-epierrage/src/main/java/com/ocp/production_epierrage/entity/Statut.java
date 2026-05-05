package com.ocp.production_epierrage.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "statuts")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Statut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Attributs exacts du diagramme
    @Column(unique = true, nullable = false)
    private String libelle;   // Brouillon, Terminer, Valider

    @Column(unique = true, nullable = false)
    private String code;      // BROUILLON, TERMINER, VALIDER

    // Relation inverse : Statut référencé par Arret et Production
    @OneToMany(mappedBy = "statut", fetch = FetchType.LAZY)
    private List<Arret> arrets;

    @OneToMany(mappedBy = "statut", fetch = FetchType.LAZY)
    private List<Production> productions;
}