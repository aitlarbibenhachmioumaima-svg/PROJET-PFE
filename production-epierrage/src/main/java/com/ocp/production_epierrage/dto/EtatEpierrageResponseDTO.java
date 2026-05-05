package com.ocp.production_epierrage.dto;
import java.time.LocalDateTime;

public record EtatEpierrageResponseDTO(
        Long id,
        LocalDateTime dateLocale,
        TasResponseDTO tas,
        String utilisateurLogin
) {}