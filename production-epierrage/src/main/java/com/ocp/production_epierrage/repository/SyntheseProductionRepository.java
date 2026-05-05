package com.ocp.production_epierrage.repository;


import com.ocp.production_epierrage.entity.SyntheseProduction;
import com.ocp.production_epierrage.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SyntheseProductionRepository extends JpaRepository<SyntheseProduction, Long> {

    Optional<SyntheseProduction> findByDateLocale(LocalDate date);
    List<SyntheseProduction> findByDateLocaleBetweenOrderByDateLocaleAsc(LocalDate debut, LocalDate fin);
    List<SyntheseProduction> findByOperateur(Utilisateur operateur);
    Optional<SyntheseProduction> findTopByOrderByDateLocaleDesc();

    @Query("SELECT s FROM SyntheseProduction s LEFT JOIN FETCH s.details " +
            "WHERE s.dateLocale BETWEEN :d AND :f ORDER BY s.dateLocale")
    List<SyntheseProduction> findByPeriodeWithDetails(
            @Param("d") LocalDate debut, @Param("f") LocalDate fin);
}