package com.ocp.production_epierrage.dto;

import jakarta.validation.constraints.NotNull;

public record KPIDetailRequestDTO(
        @NotNull Long typeId,      // id du KPIType
        @NotNull Double valeur,
        String unite
) {}