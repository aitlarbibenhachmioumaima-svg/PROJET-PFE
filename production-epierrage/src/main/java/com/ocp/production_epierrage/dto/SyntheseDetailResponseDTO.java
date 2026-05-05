package com.ocp.production_epierrage.dto;
public record SyntheseDetailResponseDTO(
        Long id,
        Integer poste,
        String indicateurCode,
        String indicateurLibelle,
        Double valeur,
        String unite
) {}