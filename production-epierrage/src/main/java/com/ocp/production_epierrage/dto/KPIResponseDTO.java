package com.ocp.production_epierrage.dto;

import java.time.LocalDate;
import java.util.List;

public record KPIResponseDTO(
        Long id,
        LocalDate dateLocale,
        Integer semaine,
        String utilisateurLogin,
        List<KPIDetailResponseDTO> details
) {}