package com.ocp.production_epierrage.entity;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "kpi_details")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class KPIDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Attributs exacts du diagramme (table KPIDetail)

    // Relation : kpi_id (FK) → KPI
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kpi_id", nullable = false)
    private KPI kpi;

    // Relation : type_id (FK) → KPI_Type  (0..* → 1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", nullable = false)
    private KPIType type;

    private Double valeur;      // decimal

    private String unite;       // varchar (h, T, T/h, %, nb)
}