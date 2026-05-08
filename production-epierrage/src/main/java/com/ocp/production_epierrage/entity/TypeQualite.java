package com.ocp.production_epierrage.entity;



import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "type_qualites")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TypeQualite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true, nullable = false)
    private String libelle;

    @Column(name = "code_couleur", nullable = false)
    private String codeCouleur;


    @OneToMany(mappedBy = "qualite", fetch = FetchType.LAZY)
    private List<Tas> tas;
}
