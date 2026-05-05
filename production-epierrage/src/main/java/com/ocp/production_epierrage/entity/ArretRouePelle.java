package com.ocp.production_epierrage.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "arrets_roue_pelle")
@PrimaryKeyJoinColumn(name = "arret_id")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ArretRouePelle extends Arret {

    // Attributs spécifiques ArretRouePelle — exacts du diagramme image 1
    private String couche;         // varchar (C1, PS, B2/28, A1/69...)

    private Double thc;            // decimal (THC mis à jour)

    @Column(name = "mise_a_jour")
    private String miseAJour;      // varchar

    private String organe;         // varchar (ROUE PELLE)

    private String atelier;        // varchar (MEC, ELEC, EXP)

    // Note : dateDebut, dateFin, duree, utilisateur, statut, type
    // sont hérités de Arret
}