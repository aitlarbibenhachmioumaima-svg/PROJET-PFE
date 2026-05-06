package com.ocp.production_epierrage.Mappers;


import com.ocp.production_epierrage.dto.ProductionResponseDTO;
import com.ocp.production_epierrage.entity.Production;
import org.springframework.stereotype.Component;

@Component
public class ProductionMapper {

    public ProductionResponseDTO toResponseDTO(Production p) {
        return new ProductionResponseDTO(
                p.getId(),
                p.getDateLocale(),
                p.getPoste(),
                p.getCouche(),
                p.getOrigine(),
                p.getQualite() != null ? p.getQualite().getLibelle() : null,
                p.getEmplacement(),
                p.getCodeEchantillon(),
                p.getCodeSct(),
                p.getThe(),
                p.getCoefficient(),
                p.getThc(),
                p.getDebutStockage(),
                p.getFinStockage(),
                p.getHm(),
                p.getDebit(),
                p.getEtat(),
                p.getCommentaire(),
                p.getStatut() != null ? p.getStatut().getLibelle() : null,
                p.getOperateur() != null ? p.getOperateur().getLogin() : null
        );
    }
}