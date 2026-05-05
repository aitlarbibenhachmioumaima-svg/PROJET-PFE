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

    // Attributs exacts du diagramme (table KPI_Type)
    @Column(unique = true, nullable = false)
    private String libelle;    // HM EP, Tonnage, Arrêts MEC, TD Mécanique, MTBF, OEE...

    @Column(unique = true, nullable = false)
    private String code;       // HM_EP, TONNAGE, MEC, ELEC, TD_MEC, MTBF, OEE, NB_PANNES...

    // Relation inverse : KPIType référencé par KPIDetail (0..* → 1)
    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    private List<KPIDetail> details;
}