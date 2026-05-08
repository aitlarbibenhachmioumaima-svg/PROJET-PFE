package com.ocp.production_epierrage.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "arrets_roue_pelle")
@PrimaryKeyJoinColumn(name = "arret_id")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ArretRouePelle extends Arret {


    private String couche;

    private Double thc;

    @Column(name = "mise_a_jour")
    private String miseAJour;

    private String organe;

    private String atelier;


}