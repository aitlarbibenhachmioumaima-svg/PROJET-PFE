package com.ocp.production_epierrage.dto;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public record SyntheseStatsResponseDTO(
        LocalDate dateDebut,
        LocalDate dateFin,
        // Totaux période
        Map<String, Double> totaux,         // THE_TOTAL, THC_TOTAL, HM_TOTAL
        // Moyennes journalières
        Map<String, Double> moyennes,       // THE_MOY, THC_MOY, HM_MOY, REND_MOY
        // Min / Max
        Map<String, Double> minMax,         // THE_MIN, THE_MAX, THC_MIN, THC_MAX
        // Stats par poste (code → poste → valeur)
        Map<String, Map<Integer, Double>> statsByPoste,
        // Séries temporelles pour graphes
        List<String> labels,
        Map<String, List<Double>> series,   // THE_P1, THE_P2, THE_P3, THE_J, THC_J...
        // Données brutes pour tableau
        List<SyntheseResponseDTO> tableau
) {}