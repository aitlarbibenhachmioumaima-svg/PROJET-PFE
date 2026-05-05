package com.ocp.production_epierrage.dto;


import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record ArretRouePelleRequestDTO(
        @NotNull LocalDateTime dateDebut,
        @NotNull LocalDateTime dateFin,
        Long typeId,
        String cause,
        String equipement,
        String description,
        Long utilisateurId,
        Long statutId,
        // Spécifique RPL
        String couche,
        Double thc,
        String miseAJour,
        String organe,
        String atelier
) {}