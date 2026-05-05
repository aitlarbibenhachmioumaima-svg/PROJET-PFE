package com.ocp.production_epierrage.dto;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public record ArretEpierrageRequestDTO(
        @NotNull LocalDateTime dateDebut,
        @NotNull LocalDateTime dateFin,
        Long typeId,
        String cause,
        @NotBlank String equipement,
        String description,
        Long utilisateurId,
        Long statutId,
        // Spécifique ArretEpierrage
        String semaine,
        @Min(1) @Max(3) Integer poste,
        String chaine,
        String atelier,
        String panne,
        Boolean frequence
) {}