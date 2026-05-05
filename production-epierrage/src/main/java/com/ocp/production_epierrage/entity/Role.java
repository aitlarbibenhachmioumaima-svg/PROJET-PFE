package com.ocp.production_epierrage.entity;



import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Attributs exacts du diagramme
    @Column(unique = true, nullable = false)
    private String libelle;   // ADMIN, Chef de Poste, Opérateur, Responsable

    @Column(unique = true, nullable = false)
    private String code;      // ADMIN, CDP, OPE, RESP

    // Relation inverse (optionnelle, non visible dans diagramme mais utile)
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<Utilisateur> utilisateurs;
}