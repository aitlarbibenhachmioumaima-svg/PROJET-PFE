package com.ocp.production_epierrage.mappers;


import com.ocp.production_epierrage.dto.KPIDetailResponseDTO;
import com.ocp.production_epierrage.dto.KPIResponseDTO;
import com.ocp.production_epierrage.entity.KPI;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class KPIMapper {

    public KPIResponseDTO toResponseDTO(KPI k) {
        List<KPIDetailResponseDTO> details = k.getDetails() == null ? List.of() :
                k.getDetails().stream().map(d ->
                        new KPIDetailResponseDTO(
                                d.getId(),
                                d.getType() != null ? d.getType().getCode() : null,
                                d.getType() != null ? d.getType().getLibelle() : null,
                                d.getValeur(),
                                d.getUnite()
                        )
                ).collect(Collectors.toList());

        return new KPIResponseDTO(
                k.getId(),
                k.getDateLocale(),
                k.getSemaine(),
                k.getUtilisateur() != null ? k.getUtilisateur().getLogin() : null,
                details
        );
    }
}
