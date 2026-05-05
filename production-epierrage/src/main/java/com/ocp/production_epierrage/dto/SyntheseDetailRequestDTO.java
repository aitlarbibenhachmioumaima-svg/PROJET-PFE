package com.ocp.production_epierrage.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SyntheseDetailRequestDTO(
        @Min(1) @Max(3) Integer poste,   // null = journée totale
        @NotNull Long indicateurId,       // id de l'IndicateurSynthese
        @NotNull Double valeur,
        String unite
) {}