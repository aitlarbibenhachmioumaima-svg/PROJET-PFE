package com.ocp.production_epierrage.entity;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "kpis")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class KPI {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Attributs exacts du diagramme (table KPI)
    @Column(name = "date_locale")
    private LocalDate dateLocale;      // date

    private Integer semaine;           // int (numéro de semaine : 1, 2, 3...)

    // Relation : KPI → KPIDetail  (1 → 1..*)
    @OneToMany(mappedBy = "kpi", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<KPIDetail> details = new ArrayList<>();

    // Relation : KPI → Utilisateur via visualise (implicite dans diagramme)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
}