package com.ocp.production_epierrage.dto;


public record TasResponseDTO(
        Long id,
        Integer numeroDuTas,
        String coucheOuTranche,
        Double tonnage,
        String statutCouleur,
        String zone,
        String qualiteLibelle,
        String qualiteCodeCouleur
) {}