package com.ocp.production_epierrage.repository;


import com.ocp.production_epierrage.entity.KPI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface KPIRepository extends JpaRepository<KPI, Long> {

    Optional<KPI> findByDateLocale(LocalDate date);
    List<KPI> findByDateLocaleBetweenOrderByDateLocaleAsc(LocalDate debut, LocalDate fin);
    List<KPI> findBySemaine(Integer semaine);
    Optional<KPI> findTopByOrderByDateLocaleDesc();

    @Query("SELECT k FROM KPI k LEFT JOIN FETCH k.details WHERE k.dateLocale BETWEEN :d AND :f")
    List<KPI> findByPeriodeWithDetails(
            @Param("d") LocalDate debut, @Param("f") LocalDate fin);
}