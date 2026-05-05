package com.ocp.production_epierrage.dto;
public record KPIDetailResponseDTO(
        Long id,
        String typeCode,
        String typeLibelle,
        Double valeur,
        String unite
) {}