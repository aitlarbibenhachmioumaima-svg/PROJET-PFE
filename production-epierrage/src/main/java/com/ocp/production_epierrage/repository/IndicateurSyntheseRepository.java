package com.ocp.production_epierrage.repository;


import com.ocp.production_epierrage.entity.IndicateurSynthese;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IndicateurSyntheseRepository extends JpaRepository<IndicateurSynthese, Long> {
    Optional<IndicateurSynthese> findByCode(String code);
}