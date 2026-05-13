package com.ocp.production_epierrage.dto;


import com.ocp.production_epierrage.entity.Statut;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record ProductionRequestDTO(

        @NotNull(message = "La date est obligatoire")
        LocalDate dateLocale,

        Integer poste,

        String chefPoste,

        String couche,

        String origine,

        String emplacement,

        String codeEchantillon,

        String OCPSCT,

        String qualité,

        String stacker,

        String chaine,

        Double the,

        Double coefficient,

        Double hm,

        String etat,

        String commentaire,

        LocalTime debutStockage,

        LocalTime finStockage,

        Statut statut,

        Long operateurId

) {
}