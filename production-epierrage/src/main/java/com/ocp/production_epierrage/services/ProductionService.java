package com.ocp.production_epierrage.services;

import com.ocp.production_epierrage.dto.ProductionRequestDTO;
import com.ocp.production_epierrage.dto.ProductionResponseDTO;
import com.ocp.production_epierrage.entity.Production;
import com.ocp.production_epierrage.entity.Statut;
import com.ocp.production_epierrage.entity.Utilisateur;


import com.ocp.production_epierrage.mappers.ProductionMapper;
import com.ocp.production_epierrage.repository.ProductionRepository;
import com.ocp.production_epierrage.repository.UtilisateurRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductionService {

    private final ProductionRepository productionRepository;

    private final UtilisateurRepository utilisateurRepository;

    private final ProductionMapper productionMapper;

    public ProductionService(
            ProductionRepository productionRepository,
            UtilisateurRepository utilisateurRepository,
            ProductionMapper productionMapper
    ) {

        this.productionRepository = productionRepository;

        this.utilisateurRepository = utilisateurRepository;

        this.productionMapper = productionMapper;
    }

    /*
     =========================================
     AJOUTER PRODUCTION
     =========================================
     */

    public ProductionResponseDTO addProduction(
            ProductionRequestDTO dto
    ) {

        Utilisateur operateur =
                utilisateurRepository.findById(dto.operateurId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Operateur introuvable"
                                ));

        Production production =
                productionMapper.toEntity(
                        dto,
                        operateur
                );

        /*
         Si statut null
         => BROUILLON automatiquement
         */

        if (production.getStatut() == null) {

            production.setStatut(
                    Statut.BROUILLON
            );
        }

        Production savedProduction =
                productionRepository.save(
                        production
                );

        return productionMapper.toResponseDTO(
                savedProduction
        );
    }

    /*
     =========================================
     RECUPERER TOUTES LES PRODUCTIONS
     =========================================
     */

    public List<ProductionResponseDTO>
    getAllProductions() {

        return productionRepository.findAll()
                .stream()
                .map(productionMapper::toResponseDTO)
                .toList();
    }

    /*
     =========================================
     RECUPERER PRODUCTION PAR ID
     =========================================
     */

    public ProductionResponseDTO
    getProductionById(Long id) {

        Production production =
                productionRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Production introuvable avec l'id : "
                                                + id
                                ));

        return productionMapper.toResponseDTO(
                production
        );
    }

    /*
     =========================================
     MODIFIER PRODUCTION
     =========================================
     */

    public ProductionResponseDTO updateProduction(
            Long id,
            ProductionRequestDTO dto
    ) {

        Production production =
                productionRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Production introuvable avec l'id : "
                                                + id
                                ));

        /*
         Si statut VALIDE
         modification impossible
         */

        if (production.getStatut()
                == Statut.VALIDE) {

            throw new RuntimeException(
                    "Modification impossible : production validée"
            );
        }

        Utilisateur operateur =
                utilisateurRepository.findById(dto.operateurId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Operateur introuvable"
                                ));

        productionMapper.updateEntityFromDTO(
                dto,
                production,
                operateur
        );

        Production updatedProduction =
                productionRepository.save(
                        production
                );

        return productionMapper.toResponseDTO(
                updatedProduction
        );
    }

    /*
     =========================================
     SUPPRIMER PRODUCTION
     =========================================
     */

    public void deleteProduction(Long id) {

        Production production =
                productionRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Production introuvable avec l'id : "
                                                + id
                                ));

        productionRepository.delete(
                production
        );
    }

    /*
     =========================================
     RECHERCHE PAR DATE
     =========================================
     */

    public List<ProductionResponseDTO>
    getByDate(LocalDate date) {

        return productionRepository
                .findByDateLocale(date)
                .stream()
                .map(productionMapper::toResponseDTO)
                .toList();
    }

    /*
     =========================================
     RECHERCHE PAR POSTE
     =========================================
     */

    public List<ProductionResponseDTO>
    getByPoste(Integer poste) {

        return productionRepository
                .findByPoste(poste)
                .stream()
                .map(productionMapper::toResponseDTO)
                .toList();
    }

    /*
     =========================================
     RECHERCHE PAR STATUT
     =========================================
     */

    public List<ProductionResponseDTO>
    getByStatut(Statut statut) {

        return productionRepository
                .findByStatut(statut)
                .stream()
                .map(productionMapper::toResponseDTO)
                .toList();
    }

    /*
     =========================================
     RECHERCHE PAR OPERATEUR
     =========================================
     */

    public List<ProductionResponseDTO>
    getByOperateur(Long operateurId) {

        return productionRepository
                .findByOperateurId(operateurId)
                .stream()
                .map(productionMapper::toResponseDTO)
                .toList();
    }
}