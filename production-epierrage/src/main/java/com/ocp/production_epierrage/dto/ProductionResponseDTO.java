package com.ocp.production_epierrage.dto;

import java.time.LocalDateTime;

public record ProductionResponseDTO(
        Long id,
        LocalDateTime dateLocale,
        Integer poste,
        String couche,
        String origine,
        String qualiteLibelle,
        String emplacement,
        String codeEchantillon,
        String codeSct,
        Double the,
        Double coefficient,
        Double thc,
        LocalDateTime debutStockage,
        LocalDateTime finStockage,
        Double hm,
        Double debit,
        String etat,
        String commentaire,
        String statutLibelle,
        String operateurLogin
) {}