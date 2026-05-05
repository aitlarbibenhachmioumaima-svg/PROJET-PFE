package com.ocp.production_epierrage.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public record SyntheseProductionRequestDTO(
        @NotNull LocalDate dateLocale,
        Long operateurId,
        @NotNull List<SyntheseDetailRequestDTO> details
) {}