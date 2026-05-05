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

    // Attributs exacts du diagramme (colonne Production, image 1)
    @Column(name = "date_locale")
    private LocalDateTime dateLocale;       // datetime

    private Integer poste;                   // int (1, 2, 3)

    private String couche;                   // varchar (C2, PS, C5, EXP...)

    private String origine;                  // varchar (SAFI, REP/P,B...)

    // Relation : qualite_id (FK) → TypeQualite
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qualite_id")
    private TypeQualite qualite;

    private String emplacement;              // varchar (B2/66, A2/04...)

    @Column(name = "code_echantillon")
    private String codeEchantillon;          // varchar (E5085, E5086...)

    @Column(name = "code_sct")
    private String codeSct;                  // varchar (OCP, SCT)

    // Relation : stacker_id (FK) → Tas
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stacker_id")
    private Tas stacker;

    // Relation : chaine_id (FK) → Tas
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chaine_id")
    private Tas chaine;

    private Double the;                      // decimal (Tonnage Humide Epierré)

    private Double coefficient;              // decimal (0.94 par défaut)

    private Double thc;                      // decimal (calculé : the × coefficient)

    private Double debit;                    // decimal (calculé : thc / hm)

    @Column(name = "debut_stockage")
    private LocalDateTime debutStockage;     // datetime

    @Column(name = "fin_stockage")
    private LocalDateTime finStockage;       // datetime

    private Double hm;                       // decimal (Heures de Marche)

    private String etat;                     // varchar (Validé, Attente camions)

    @Column(columnDefinition = "text")
    private String commentaire;              // text

    // Relation : statut_id (FK) → Statut
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "statut_id")
    private Statut statut;

    // Relation : operateur_id (FK) → Utilisateur
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operateur_id")
    private Utilisateur operateur;

    // Calcul automatique THC et Débit avant persist/update
    @PrePersist
    @PreUpdate
    public void calculer() {
        if (the != null && coefficient != null)
            this.thc = Math.round(the * coefficient * 100.0) / 100.0;
        if (thc != null && hm != null && hm > 0)
            this.debit = Math.round(thc / hm * 100.0) / 100.0;
    }
}