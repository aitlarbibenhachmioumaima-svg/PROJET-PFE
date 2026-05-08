package com.ocp.production_epierrage.entity;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "productions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Production {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "date_locale")
    private LocalDateTime dateLocale;
    private Integer poste;

    private String couche;

    private String origine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qualite_id")
    private TypeQualite qualite;

    private String emplacement;
    @Column(name = "code_echantillon")
    private String codeEchantillon;

    @Column(name = "code_sct")
    private String codeSct;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", length = 20)
    private StatutEnum statut = StatutEnum.BROUILLON;

    @ManyToOne
    @JoinColumn(name = "operateur_id")
    private Utilisateur operateur;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stacker_id")
    private Tas stacker;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chaine_id")
    private Tas chaine;

    private Double the;

    private Double coefficient;

    private Double thc;

    private Double debit;

    @Column(name = "debut_stockage")
    private LocalDateTime debutStockage;

    @Column(name = "fin_stockage")
    private LocalDateTime finStockage;

    private Double hm;

    private String etat;

    @Column(columnDefinition = "text")
    private String commentaire;



    @PrePersist
    @PreUpdate
    public void calculer() {
        if (the != null && coefficient != null)
            this.thc = Math.round(the * coefficient * 100.0) / 100.0;
        if (thc != null && hm != null && hm > 0)
            this.debit = Math.round(thc / hm * 100.0) / 100.0;
    }
}