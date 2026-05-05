package com.ocp.production_epierrage.entity;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "syntheses_production")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SyntheseProduction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Attributs exacts du diagramme (table SyntheseProduction)
    @Column(name = "date_locale")
    private LocalDate dateLocale;          // date

    // Relation : operateur_id (FK) → Utilisateur
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operateur_id")
    private Utilisateur operateur;

    // Relation : SyntheseProduction → SyntheseDetail  (1 → 1..*)
    @OneToMany(mappedBy = "synthese", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<SyntheseDetail> details = new ArrayList<>();
}