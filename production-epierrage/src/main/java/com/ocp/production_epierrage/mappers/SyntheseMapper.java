package com.ocp.production_epierrage.mappers;



import com.ocp.production_epierrage.dto.response.SyntheseDetailResponseDTO;
import com.ocp.production_epierrage.dto.response.SyntheseResponseDTO;
import com.ocp.production_epierrage.entity.SyntheseProduction;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SyntheseMapper {

    public SyntheseResponseDTO toResponseDTO(SyntheseProduction s) {
        List<SyntheseDetailResponseDTO> details = s.getDetails() == null ? List.of() :
                s.getDetails().stream().map(d ->
                        new SyntheseDetailResponseDTO(
                                d.getId(),
                                d.getPoste(),
                                d.getIndicateur() != null ? d.getIndicateur().getCode() : null,
                                d.getIndicateur() != null ? d.getIndicateur().getLibelle() : null,
                                d.getValeur(),
                                d.getUnite()
                        )
                ).collect(Collectors.toList());

        return new SyntheseResponseDTO(
                s.getId(),
                s.getDateLocale(),
                s.getOperateur() != null ? s.getOperateur().getLogin() : null,
                details
        );
    }
}
