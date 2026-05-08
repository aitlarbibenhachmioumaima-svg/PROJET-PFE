package com.ocp.production_epierrage.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "arrets_epierrage")
@PrimaryKeyJoinColumn(name = "arret_id")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ArretEpierrage extends Arret {


    private Integer semaine;

    private Integer poste;

    private String chaine;
    private String equipement2;

    private String atelier;

    private String panne;

    private Boolean frequence;

    
}