package com.ocp.production_epierrage.dto;

import jakarta.validation.constraints.NotNull;

public record TasRequestDTO(
        @NotNull Integer numeroDuTas,
        String coucheOuTranche,
        Double tonnage,
        String statutCouleur,
        String zone,
        Long qualiteId
) {}