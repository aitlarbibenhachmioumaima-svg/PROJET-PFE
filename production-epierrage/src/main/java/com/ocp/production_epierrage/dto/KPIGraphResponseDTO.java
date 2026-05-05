package com.ocp.production_epierrage.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public record KPIGraphResponseDTO(
        LocalDate dateDebut,
        LocalDate dateFin,
        List<String> labels,               // dates formatées
        Map<String, List<Double>> series,  // code → valeurs (HM_EP, MEC, TD_MEC, OEE...)
        Map<String, Double> totaux,        // totaux par type
        Map<String, Double> moyennes,      // moyennes par type
        Integer totalNbrePannes,
        List<KPIResponseDTO> tableau       // données brutes pour tableau
) {}