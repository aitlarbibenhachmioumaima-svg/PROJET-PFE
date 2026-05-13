package com.ocp.production_epierrage.dto;


import com.ocp.production_epierrage.entity.Statut;

import java.time.LocalDate;
import java.time.LocalTime;

public record ProductionResponseDTO(

        Long id,

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

        Double thc,

        Double debit,

        Double hm,

        String etat,

        String commentaire,

        LocalTime debutStockage,

        LocalTime finStockage,

        Statut statut,

        Long operateurId,

        String nomOperateur

) {
}