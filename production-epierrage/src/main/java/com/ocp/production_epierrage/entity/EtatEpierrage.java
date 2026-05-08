package com.ocp.production_epierrage.entity;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "etats_epierrage")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EtatEpierrage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "date_locale")
    private LocalDate dateLocale;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tas_id")
    private Tas tas;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
}