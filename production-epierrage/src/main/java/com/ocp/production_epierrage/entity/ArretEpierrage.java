package com.ocp.production_epierrage.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "arrets_epierrage")
@PrimaryKeyJoinColumn(name = "arret_id")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ArretEpierrage extends Arret {

    // Attributs spécifiques ArretEpierrage — exacts du diagramme image 1
    private Integer semaine;      // int  (S1=1, S2=2...)

    private Integer poste;        // int  (1, 2, 3)

    private String chaine;        // varchar (CVB2, CVB5, GO, CR2, CONC...)

    private String equipement2;   // varchar (remplace equipement hérité si besoin)

    private String atelier;       // varchar (MEC, ELEC, EXP)

    private String panne;         // varchar (description panne)

    private Boolean frequence;    // boolean

    // Note : equipement, description, cause, type, utilisateur, statut
    // sont hérités de Arret
}