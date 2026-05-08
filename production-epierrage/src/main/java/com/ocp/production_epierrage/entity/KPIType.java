package com.ocp.production_epierrage.entity;



import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "kpi_types")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class KPIType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true, nullable = false)
    private String libelle;

    @Column(unique = true, nullable = false)
    private String code;


    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    private List<KPIDetail> details;
}