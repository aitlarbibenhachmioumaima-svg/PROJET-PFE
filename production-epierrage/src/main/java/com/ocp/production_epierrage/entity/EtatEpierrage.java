package com.ocp.production_epierrage.entity;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "etats_epierrage")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EtatEpierrage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Attributs exacts du diagramme
    @Column(name = "date_locale")
    private LocalDateTime dateLocale;   // datetime dans le diagramme

    // Relation ManyToOne : EtatEpierrage → Tas  (tas_id FK, 0..* → 1..*)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tas_id")
    private Tas tas;

    // Relation ManyToOne : EtatEpierrage → Utilisateur  (utilisateur_id FK)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
}