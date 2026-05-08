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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kpi_id", nullable = false)
    private KPI kpi;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", nullable = false)
    private KPIType type;

    private Double valeur;

    private String unite;
}