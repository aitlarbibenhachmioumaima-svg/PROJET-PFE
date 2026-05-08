package com.ocp.production_epierrage.entity;



import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
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
    private LocalDate dateCreation = LocalDate.now();


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "utilisateur_roles",
            joinColumns = @JoinColumn(name = "utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Builder.Default
    private Set<Role> roles = new HashSet<>();


    @OneToMany(mappedBy = "operateur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Production> productions = new ArrayList<>();


    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Arret> arrets = new ArrayList<>();


    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<EtatEpierrage> etats = new ArrayList<>();


    @ManyToMany(mappedBy = "utilisateurs", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<SyntheseProduction> syntheses = new HashSet<>();
}