package com.ocp.production_epierrage.mappers;

import com.ocp.production_epierrage.dto.ProductionRequestDTO;
import com.ocp.production_epierrage.dto.ProductionResponseDTO;
import com.ocp.production_epierrage.entity.Production;
import com.ocp.production_epierrage.entity.Utilisateur;

import org.springframework.stereotype.Component;

@Component
public class ProductionMapper {

    public Production toEntity(
            ProductionRequestDTO dto,
            Utilisateur operateur
    ) {

        Production production = new Production();

        production.setDateLocale(dto.dateLocale());

        production.setPoste(dto.poste());

        production.setChefPoste(dto.chefPoste());

        production.setCouche(dto.couche());

        production.setOrigine(dto.origine());

        production.setEmplacement(dto.emplacement());

        production.setCodeEchantillon(dto.codeEchantillon());

        production.setOCPSCT(dto.OCPSCT());

        production.setQualité(dto.qualité());

        production.setStacker(dto.stacker());

        production.setChaine(dto.chaine());

        production.setThe(dto.the());

        production.setCoefficient(dto.coefficient());

        production.setHm(dto.hm());

        production.setEtat(dto.etat());

        production.setCommentaire(dto.commentaire());

        production.setDebutStockage(dto.debutStockage());

        production.setFinStockage(dto.finStockage());

        production.setStatut(dto.statut());

        production.setOperateur(operateur);

        return production;
    }

    public ProductionResponseDTO toResponseDTO(
            Production production
    ) {

        return new ProductionResponseDTO(

                production.getId(),

                production.getDateLocale(),

                production.getPoste(),

                production.getChefPoste(),

                production.getCouche(),

                production.getOrigine(),

                production.getEmplacement(),

                production.getCodeEchantillon(),

                production.getOCPSCT(),

                production.getQualité(),

                production.getStacker(),

                production.getChaine(),

                production.getThe(),

                production.getCoefficient(),

                production.getThc(),

                production.getDebit(),

                production.getHm(),

                production.getEtat(),

                production.getCommentaire(),

                production.getDebutStockage(),

                production.getFinStockage(),

                production.getStatut(),

                production.getOperateur() != null
                        ? production.getOperateur().getId()
                        : null,

                production.getOperateur() != null
                        ? production.getOperateur().getNom()
                        : null
        );
    }

    public void updateEntityFromDTO(
            ProductionRequestDTO dto,
            Production production,
            Utilisateur operateur
    ) {

        production.setDateLocale(dto.dateLocale());

        production.setPoste(dto.poste());

        production.setChefPoste(dto.chefPoste());

        production.setCouche(dto.couche());

        production.setOrigine(dto.origine());

        production.setEmplacement(dto.emplacement());

        production.setCodeEchantillon(dto.codeEchantillon());

        production.setOCPSCT(dto.OCPSCT());

        production.setQualité(dto.qualité());

        production.setStacker(dto.stacker());

        production.setChaine(dto.chaine());

        production.setThe(dto.the());

        production.setCoefficient(dto.coefficient());

        production.setHm(dto.hm());

        production.setEtat(dto.etat());

        production.setCommentaire(dto.commentaire());

        production.setDebutStockage(dto.debutStockage());

        production.setFinStockage(dto.finStockage());

        production.setStatut(dto.statut());

        production.setOperateur(operateur);
    }
}