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


    @Column(name = "date_locale")
    private LocalDate dateLocale;

    private Integer semaine;





    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
}