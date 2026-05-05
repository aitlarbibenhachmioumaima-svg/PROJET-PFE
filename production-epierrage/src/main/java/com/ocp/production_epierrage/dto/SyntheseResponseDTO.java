package com.ocp.production_epierrage.dto;
import java.time.LocalDate;
import java.util.List;

public record SyntheseResponseDTO(
        Long id,
        LocalDate dateLocale,
        String operateurLogin,
        List<SyntheseDetailResponseDTO> details
) {}