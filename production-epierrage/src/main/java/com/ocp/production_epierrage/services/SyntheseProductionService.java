package com.ocp.production_epierrage.services;



import lombok.RequiredArgsConstructor;
import com.ocp.production_epierrage.dto.SyntheseDetailRequestDTO;
import com.ocp.production_epierrage.dto.SyntheseProductionRequestDTO;
import com.ocp.production_epierrage.dto.*;
import com.ocp.production_epierrage.entity.*;
import com.ocp.production_epierrage.exception.ResourceNotFoundException;
import com.ocp.production_epierrage.mapper.SyntheseMapper;
import com.ocp.production_epierrage.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SyntheseProductionService {

    private final SyntheseProductionRepository repository;
    private final SyntheseDetailRepository detailRepository;
    private final IndicateurSyntheseRepository indicateurRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final SyntheseMapper mapper;

    public SyntheseResponseDTO saisir(SyntheseProductionRequestDTO dto) {
        Utilisateur operateur = dto.operateurId() != null
                ? utilisateurRepository.findById(dto.operateurId()).orElseThrow()
                : null;

        SyntheseProduction s = SyntheseProduction.builder()
                .dateLocale(dto.dateLocale()).operateur(operateur).build();

        if (dto.details() != null) {
            for (SyntheseDetailRequestDTO detailDto : dto.details()) {
                IndicateurSynthese ind = indicateurRepository.findById(detailDto.indicateurId())
                        .orElseThrow(() -> new ResourceNotFoundException("Indicateur introuvable"));
                SyntheseDetail detail = SyntheseDetail.builder()
                        .synthese(s).poste(detailDto.poste())
                        .indicateur(ind).valeur(detailDto.valeur()).unite(detailDto.unite())
                        .build();
                s.getDetails().add(detail);
            }
        }
        return mapper.toResponseDTO(repository.save(s));
    }

    @Transactional(readOnly = true)
    public List<SyntheseResponseDTO> findByPeriode(LocalDate debut, LocalDate fin) {
        return repository.findByPeriodeWithDetails(debut, fin)
                .stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public SyntheseResponseDTO findByDate(LocalDate date) {
        return repository.findByDateLocale(date)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Synthèse introuvable pour : " + date));
    }

    // Statistiques complètes (image 4 → tableau + graphes)
    @Transactional(readOnly = true)
    public SyntheseStatsResponseDTO getStats(LocalDate debut, LocalDate fin) {
        List<SyntheseProduction> list = repository.findByPeriodeWithDetails(debut, fin);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM");

        List<String> labels = list.stream()
                .map(s -> s.getDateLocale().format(fmt)).collect(Collectors.toList());

        // Collecter tous les indicateurs × postes
        Map<String, List<Double>> series = new LinkedHashMap<>();
        Map<String, Double> totaux = new LinkedHashMap<>();
        Map<String, Double> moyennes = new LinkedHashMap<>();
        Map<String, Double> minMax = new LinkedHashMap<>();
        Map<String, Map<Integer, Double>> statsByPoste = new LinkedHashMap<>();

        // Codes indicateurs attendus : THE, THC, HM, REND
        // Postes attendus : 1, 2, 3, null(journée)
        Set<String> codes = list.stream()
                .flatMap(s -> s.getDetails().stream())
                .map(d -> d.getIndicateur().getCode())
                .collect(Collectors.toCollection(LinkedHashSet::new));

        Set<Integer> postes = new TreeSet<>(Comparator.nullsLast(Integer::compareTo));
        list.stream().flatMap(s -> s.getDetails().stream())
                .map(SyntheseDetail::getPoste).forEach(postes::add);

        for (String code : codes) {
            for (Integer poste : postes) {
                String key = code + "_P" + (poste != null ? poste : "J");
                List<Double> vals = list.stream().map(s ->
                        s.getDetails().stream()
                                .filter(d -> code.equals(d.getIndicateur().getCode())
                                        && Objects.equals(d.getPoste(), poste))
                                .mapToDouble(d -> d.getValeur() != null ? d.getValeur() : 0.0)
                                .findFirst().orElse(0.0)
                ).collect(Collectors.toList());
                series.put(key, vals);
                double total = vals.stream().mapToDouble(Double::doubleValue).sum();
                totaux.put(key, r(total));
                moyennes.put(key, vals.isEmpty() ? 0.0 : r(total / vals.size()));
                if (!vals.isEmpty()) {
                    minMax.put(key + "_MIN", r(vals.stream().mapToDouble(Double::doubleValue).min().orElse(0)));
                    minMax.put(key + "_MAX", r(vals.stream().mapToDouble(Double::doubleValue).max().orElse(0)));
                }
            }
        }

        List<SyntheseResponseDTO> tableau = list.stream().map(mapper::toResponseDTO).collect(Collectors.toList());

        return new SyntheseStatsResponseDTO(
                debut, fin, totaux, moyennes, minMax, statsByPoste, labels, series, tableau);
    }

    public void supprimer(Long id) {
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Synthèse introuvable : " + id));
        repository.deleteById(id);
    }

    private double r(double v) { return Math.round(v * 100.0) / 100.0; }
}
