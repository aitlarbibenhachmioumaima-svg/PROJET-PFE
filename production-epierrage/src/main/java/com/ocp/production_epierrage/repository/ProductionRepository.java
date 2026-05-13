package com.ocp.production_epierrage.repository;



import com.ocp.production_epierrage.entity.Production;
import com.ocp.production_epierrage.entity.Statut;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProductionRepository
        extends JpaRepository<Production, Long> {

    List<Production> findByDateLocale(LocalDate dateLocale);

    List<Production> findByPoste(Integer poste);

    List<Production> findByStatut(Statut statut);

    List<Production> findByOperateurId(Long operateurId);
}