package com.ocp.production_epierrage.dto;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public record KPIRequestDTO(
        @NotNull LocalDate dateLocale,
        Integer semaine,
        Long utilisateurId,
        @NotNull List<KPIDetailRequestDTO> details
) {}