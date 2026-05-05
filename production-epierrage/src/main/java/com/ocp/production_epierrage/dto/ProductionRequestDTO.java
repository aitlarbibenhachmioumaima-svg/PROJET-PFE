package com.ocp.production_epierrage.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public record ProductionRequestDTO(
        @NotNull(message = "La date est obligatoire") LocalDateTime dateLocale,
        @NotNull @Min(1) @Max(3) Integer poste,
        String couche,
        String origine,
        Long qualiteId,
        String emplacement,
        String codeEchantillon,
        String codeSct,
        @NotNull @DecimalMin("0.0") Double the,
        Double coefficient,
        LocalDateTime debutStockage,
        LocalDateTime finStockage,
        @DecimalMin("0.0") Double hm,
        String etat,
        String commentaire,
        Long statutId,
        Long operateurId
) {}