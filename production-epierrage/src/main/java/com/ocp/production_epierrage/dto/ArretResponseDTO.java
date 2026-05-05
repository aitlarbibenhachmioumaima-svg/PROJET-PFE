package com.ocp.production_epierrage.dto;
import java.time.LocalDateTime;

public record ArretResponseDTO(
        Long id,
        LocalDateTime dateDebut,
        LocalDateTime dateFin,
        Double duree,
        String typeLibelle,
        String cause,
        String equipement,
        String description,
        String utilisateurLogin,
        String statutLibelle,
        // ArretEpierrage spécifique
        String semaine,
        Integer poste,
        String chaine,
        String atelier,
        String panne,
        Boolean frequence,
        // ArretRouePelle spécifique
        String couche,
        Double thc,
        String miseAJour,
        String organe
) {}