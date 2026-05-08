package com.ocp.production_epierrage.entity;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "syntheses_production")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SyntheseProduction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "date_locale")
    private LocalDate dateLocale;




    @OneToMany(mappedBy = "synthese", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<SyntheseDetail> details = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "synthese_utilisateurs",
            joinColumns = @JoinColumn(name = "synthese_id"),
            inverseJoinColumns = @JoinColumn(name = "utilisateur_id")
    )
    @Builder.Default
    private Set<Utilisateur> utilisateurs = new HashSet<>();




}