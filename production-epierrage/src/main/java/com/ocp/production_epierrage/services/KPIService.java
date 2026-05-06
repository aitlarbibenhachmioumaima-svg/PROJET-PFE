package com.ocp.production_epierrage.services;



import lombok.RequiredArgsConstructor;
import com.ocp.production_epierrage.dto.KPIDetailRequestDTO;
import com.ocp.production_epierrage.dto.KPIRequestDTO;
import com.ocp.production_epierrage.dto.KPIGraphResponseDTO;
import com.ocp.production_epierrage.dto.KPIResponseDTO;
import com.ocp.production_epierrage.entity.*;
import com.ocp.production_epierrage.exception.ResourceNotFoundException;
import com.ocp.production_epierrage.mapper.KPIMapper;
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
public class KPIService {

    private final KPIRepository kpiRepository;
    private final KPIDetailRepository kpiDetailRepository;
    private final KPITypeRepository kpiTypeRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final KPIMapper mapper;

    public KPIResponseDTO saisir(KPIRequestDTO dto, String login) {
        Utilisateur u = utilisateurRepository.findByLogin(login)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable : " + login));

        KPI kpi = KPI.builder()
                .dateLocale(dto.dateLocale())
                .semaine(dto.semaine())
                .utilisateur(u)
                .build();

        // Créer les KPIDetail dynamiques
        if (dto.details() != null) {
            for (KPIDetailRequestDTO detailDto : dto.details()) {
                KPIType type = kpiTypeRepository.findById(detailDto.typeId())
                        .orElseThrow(() -> new ResourceNotFoundException("KPIType introuvable"));
                KPIDetail detail = KPIDetail.builder()
                        .kpi(kpi).type(type)
                        .valeur(detailDto.valeur()).unite(detailDto.unite())
                        .build();
                kpi.getDetails().add(detail);
            }
        }
        return mapper.toResponseDTO(kpiRepository.save(kpi));
    }

    @Transactional(readOnly = true)
    public List<KPIResponseDTO> findByPeriode(LocalDate debut, LocalDate fin) {
        return kpiRepository.findByPeriodeWithDetails(debut, fin)
                .stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public KPIResponseDTO findById(Long id) {
        return mapper.toResponseDTO(kpiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("KPI introuvable : " + id)));
    }

    // Données structurées pour graphes Chart.js (image 6 → graphe)
    @Transactional(readOnly = true)
    public KPIGraphResponseDTO getGraphData(LocalDate debut, LocalDate fin) {
        List<KPI> kpis = kpiRepository.findByPeriodeWithDetails(debut, fin);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM");

        List<String> labels = kpis.stream()
                .map(k -> k.getDateLocale().format(fmt)).collect(Collectors.toList());

        // Construire les séries : pour chaque type KPI → liste des valeurs par date
        Map<String, List<Double>> series = new LinkedHashMap<>();
        Map<String, Double> totaux = new LinkedHashMap<>();
        Map<String, Double> moyennes = new LinkedHashMap<>();

        // Collecter tous les types présents
        Set<String> allCodes = kpis.stream()
                .flatMap(k -> k.getDetails().stream())
                .map(d -> d.getType().getCode())
                .collect(Collectors.toCollection(LinkedHashSet::new));

        for (String code : allCodes) {
            List<Double> values = kpis.stream().map(k ->
                    k.getDetails().stream()
                            .filter(d -> code.equals(d.getType().getCode()))
                            .mapToDouble(d -> d.getValeur() != null ? d.getValeur() : 0.0)
                            .findFirst().orElse(0.0)
            ).collect(Collectors.toList());
            series.put(code, values);
            double total = values.stream().mapToDouble(Double::doubleValue).sum();
            totaux.put(code, r(total));
            moyennes.put(code, values.isEmpty() ? 0.0 : r(total / values.size()));
        }

        // Total pannes (si KPI_TYPE code = "NB_PANNES")
        int totalPannes = (int) kpis.stream()
                .flatMap(k -> k.getDetails().stream())
                .filter(d -> "NB_PANNES".equals(d.getType().getCode()))
                .mapToDouble(d -> d.getValeur() != null ? d.getValeur() : 0.0)
                .sum();

        List<KPIResponseDTO> tableau = kpis.stream().map(mapper::toResponseDTO).collect(Collectors.toList());

        return new KPIGraphResponseDTO(debut, fin, labels, series, totaux, moyennes, totalPannes, tableau);
    }

    public void supprimer(Long id) {
        kpiRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("KPI introuvable : " + id));
        kpiRepository.deleteById(id);
    }

    private double r(double v) { return Math.round(v * 100.0) / 100.0; }
}