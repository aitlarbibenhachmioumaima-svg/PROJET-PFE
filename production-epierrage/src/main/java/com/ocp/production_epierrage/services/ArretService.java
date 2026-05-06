package com.ocp.production_epierrage.services;


import com.ocp.production_epierrage.dto.ArretRequest;
import com.ocp.production_epierrage.dto.ArretResponse;
import com.ocp.production_epierrage.entity.*;
import com.ocp.production_epierrage.exception.*;
import com.ocp.production_epierrage.mappers.ArretMapper;
import com.ocp.production_epierrage.repository.*;
import com.ocp.production_epierrage.repository.ArretEpierrageRepository;
import com.ocp.production_epierrage.repository.ArretRouePelleRepository;
import com.ocp.production_epierrage.repository.TypeArretRepository;
import com.ocp.production_epierrage.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.*;
import java.util.List;
import java.util.stream.Collectors;

@Service @Transactional
public class ArretService {

    @Autowired private ArretEpierrageRepository arretEpierrageRepo;
    @Autowired private ArretRouePelleRepository arretRouePelleRepo;
    @Autowired private TypeArretRepository typeArretRepository;
    @Autowired private UtilisateurRepository utilisateurRepository;
    @Autowired private ArretMapper mapper;

    private void calculerDuree(Arret arret) {
        if (arret.getDateDebut() != null && arret.getDateFin() != null) {
            if (arret.getDateFin().isBefore(arret.getDateDebut()))
                throw new BusinessException("La date de fin doit être après la date de début");
            long mins = Duration.between(arret.getDateDebut(), arret.getDateFin()).toMinutes();
            arret.setDuree(BigDecimal.valueOf(mins).divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP));
        }
    }

    public ArretResponse saisirEpierrage(ArretRequest req) {
        ArretEpierrage a = new ArretEpierrage();
        remplirBase(a, req);
        a.setSemaine(req.getSemaine());
        a.setPoste(req.getPoste());
        a.setChaine(req.getChaine());
        a.setAtelier(req.getAtelier());
        a.setPanne(req.getPanne());
        a.setFrequence(req.getFrequence());
        calculerDuree(a);
        return mapper.toResponse(arretEpierrageRepo.save(a));
    }

    public ArretResponse saisirRouePelle(ArretRequest req) {
        ArretRouePelle a = new ArretRouePelle();
        remplirBase(a, req);
        a.setCouche(req.getCouche());
        a.setOrgane(req.getOrgane());
        a.setMiseAJour(req.getMiseAJour());
        calculerDuree(a);
        return mapper.toResponse(arretRouePelleRepo.save(a));
    }

    private void remplirBase(Arret a, ArretRequest req) {
        a.setDateDebut(req.getDateDebut());
        a.setDateFin(req.getDateFin());
        a.setCause(req.getCause());
        a.setEquipement(req.getEquipement());
        a.setDescription(req.getDescription());
        if (req.getTypeId() != null)
            typeArretRepository.findById(req.getTypeId()).ifPresent(a::setType);
        if (req.getUtilisateurId() != null)
            utilisateurRepository.findById(req.getUtilisateurId()).ifPresent(a::setUtilisateur);
    }

    public List<ArretResponse> listerEpierrageParPeriode(LocalDateTime d, LocalDateTime f) {
        return arretEpierrageRepo.findByDateDebutBetween(d, f)
                .stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    public List<ArretResponse> listerRouePelleParPeriode(LocalDateTime d, LocalDateTime f) {
        return arretRouePelleRepo.findByDateDebutBetween(d, f)
                .stream().map(mapper::toResponse).collect(Collectors.toList());
    }
}
