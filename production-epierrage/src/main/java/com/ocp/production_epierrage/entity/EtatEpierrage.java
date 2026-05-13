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


    @Column(name = "numero_tas", nullable = false)
    private String numeroTas;

    @Column(name = "couche_tranchee", nullable = false)
    private String coucheTranchee;

    @Column(nullable = false)
    private Double tonnage;



    @Column(nullable = false)
    private String qualite;

    @Column(name = "ordre_affichage")
    private Integer ordreAffichage;

    @Column(nullable = false)
    private String zone;

    @Column(name = "is_stacker_here")
    private boolean isStackerHere;

    @Column(name = "is_rouebelle_here")
    private boolean isRouebelleHere;

    @Column(name = "nom_machine")
    private String nomMachine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
}