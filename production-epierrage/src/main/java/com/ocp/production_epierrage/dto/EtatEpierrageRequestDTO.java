package com.ocp.production_epierrage.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record EtatEpierrageRequestDTO(
        @NotNull LocalDateTime dateLocale,
        @NotNull Long tasId,
        Long utilisateurId
) {}