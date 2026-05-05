package com.ocp.production_epierrage.repository;


import com.ocp.production_epierrage.entity.EtatEpierrage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EtatEpierrageRepository extends JpaRepository<EtatEpierrage, Long> {

    List<EtatEpierrage> findByDateLocaleBetweenOrderByDateLocaleDesc(
            LocalDateTime debut, LocalDateTime fin);

    Optional<EtatEpierrage> findTopByOrderByDateLocaleDesc();

    @Query("SELECT e FROM EtatEpierrage e WHERE e.tas.zone = :zone " +
            "AND e.dateLocale BETWEEN :debut AND :fin ORDER BY e.dateLocale DESC")
    List<EtatEpierrage> findByZoneAndPeriode(
            @Param("zone") String zone,
            @Param("debut") LocalDateTime debut,
            @Param("fin") LocalDateTime fin);
}