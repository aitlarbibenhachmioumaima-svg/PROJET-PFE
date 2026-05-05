package com.ocp.production_epierrage.entity;



import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "utilisateurs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Attributs exacts du diagramme
    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nom;

    private String prenom;

    @Column(unique = true)
    private String email;

    @Builder.Default
    private Boolean actif = true;

    @Column(name = "date_creation")
    @Builder.Default
    private LocalDateTime dateCreation = LocalDateTime.now();

    // Relation ManyToMany via table UtilisateurRole (table de jointure visible dans diagramme)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "utilisateur_roles",              // table UtilisateurRole du diagramme
            joinColumns = @JoinColumn(name = "utilisateur_id"),   // FK1
            inverseJoinColumns = @JoinColumn(name = "role_id")    // FK2
    )
    @Builder.Default
    private Set<Role> roles = new HashSet<>();

    // Relation : Utilisateur saisit Production (1 → 0..*)
    @OneToMany(mappedBy = "operateur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Production> productions = new ArrayList<>();

    // Relation : Utilisateur enregistre Arret (1 → 0..*)
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Arret> arrets = new ArrayList<>();

    // Relation : Utilisateur saisit/porte EtatEpierrage (1 → 0..*)
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<EtatEpierrage> etats = new ArrayList<>();

    // Relation : Utilisateur visualise KPI (1 → 0..*)
    // (non mappé directement ici, géré via KPI.utilisateur)

    // Relation : Utilisateur consulte SyntheseProduction (1 → 0..*)
    @OneToMany(mappedBy = "operateur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<SyntheseProduction> syntheses = new ArrayList<>();
}