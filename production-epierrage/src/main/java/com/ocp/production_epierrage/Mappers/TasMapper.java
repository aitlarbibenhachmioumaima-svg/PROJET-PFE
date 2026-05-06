package com.ocp.production_epierrage.Mappers;



import com.ocp.production_epierrage.dto.TasResponseDTO;
import com.ocp.production_epierrage.entity.Tas;
import org.springframework.stereotype.Component;

@Component
public class TasMapper {

    public TasResponseDTO toResponseDTO(Tas t) {
        return new TasResponseDTO(
                t.getId(),
                t.getNumeroDuTas(),
                t.getCoucheOuTranche(),
                t.getTonnage(),
                t.getStatutCouleur(),
                t.getZone(),
                t.getQualite() != null ? t.getQualite().getLibelle() : null,
                t.getQualite() != null ? t.getQualite().getCodeCouleur() : null
        );
    }
}
