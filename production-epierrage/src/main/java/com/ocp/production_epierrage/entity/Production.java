package com.ocp.production_epierrage.entity;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "productions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Production {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_locale")
    private LocalDate dateLocale;

    private Integer poste;

    private String chefPoste;

    private String couche;

    private String origine;

    private String emplacement;

    @Column(name = "code_echantillon")
    private String codeEchantillon;

    @Column(name = "code_sct")
    private String OCPSCT;

    private String qualité;

    private String stacker;

    private  String chaine;

    private Double the;

    private Double coefficient;

    private Double thc;

    private Double debit;

    @Column(name = "debut_stockage")
    private LocalTime debutStockage;

    @Column(name = "fin_stockage")
    private LocalTime finStockage;

    private Double hm;

    private String etat;

    @Column(columnDefinition = "text")
    private String commentaire;


    @Enumerated(EnumType.STRING)
    @Column(name = "statut", length = 20)
    private StatutEnum statut = StatutEnum.BROUILLON;

    @ManyToOne
    @JoinColumn(name = "operateur_id")
    private Utilisateur operateur;
    @PrePersist
    @PreUpdate



    public void calculer() {
        if (the != null && coefficient != null)
            this.thc = Math.round(the * coefficient * 100.0) / 100.0;
        if (thc != null && hm != null && hm > 0)
            this.debit = Math.round(thc / hm * 100.0) / 100.0;
    }
}