package com.ocp.production_epierrage.entity;



import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "kpis")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class KPI {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;



    @Column(name = "hm_ep")
    private Double hmEp;
    @Column(name = "rdt_p")
    private Double rdtP;

    @Column(name = "rdt_r")
    private Double rdtR;


    @Column(name = "td_mecanique")
    private Double tdMecanique;
    @Column(name = "tu_utilisation")
    private Double tu;

    @Column(name = "td_exploitation")
    private Double tdExploitation;



    private Double arretMecanique;
    private Double arretElectrique;
    private Double arretElectronique;
    private Double arretExploitation;
    private Double arretMateriel;
    private Double arretDecide;
    private Double arretKoch;
    private Double arretPlanifie;
    private Double arretExterieur;
    private Double arretAttenteCamion;


    @Column(name = "a_exp")
    private Double aExp;
    @Column(name = "a_ext")
    private Double aExt;
    @Column(name = "a_mat")
    private Double aMat;
    @Column(name = "t_dis")
    private Double tDis;
    @Column(name = "taux_utilisation_detail")
    private Double tauxUtilisation;



    private Double tonnage;

    @Column(name = "the")
    private Double tonnageHumideEpierre;

    @Column(name = "thc")
    private Double tonnageHumideCrible;
    @Column(name = "hm_marche")
    private Double heuresDeMarche;

    private Double mtbf;
    private Double debit;

    @Column(name = "nbre_pannes")
    private Integer nbrePannes;

    private Double oee;



    private String semaine;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
}





