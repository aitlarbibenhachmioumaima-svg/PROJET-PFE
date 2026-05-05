package com.ocp.production_epierrage.repository;


import com.ocp.production_epierrage.entity.Production;
import com.ocp.production_epierrage.entity.Statut;
import com.ocp.production_epierrage.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductionRepository extends JpaRepository<Production, Long> {

    List<Production> findByDateLocaleBetweenOrderByDateLocaleAscPosteAsc(
            LocalDateTime debut, LocalDateTime fin);

    List<Production> findByOperateur(Utilisateur operateur);

    List<Production> findByPosteAndDateLocaleBetween(
            Integer poste, LocalDateTime debut, LocalDateTime fin);

    List<Production> findByStatut(Statut statut);

    @Query("SELECT SUM(p.thc) FROM Production p " +
            "WHERE p.dateLocale BETWEEN :d AND :f")
    Double sumThcByPeriode(@Param("d") LocalDateTime debut, @Param("f") LocalDateTime fin);

    @Query("SELECT SUM(p.the) FROM Production p " +
            "WHERE p.dateLocale BETWEEN :d AND :f")
    Double sumTheByPeriode(@Param("d") LocalDateTime debut, @Param("f") LocalDateTime fin);

    @Query("SELECT SUM(p.hm) FROM Production p " +
            "WHERE p.dateLocale BETWEEN :d AND :f")
    Double sumHmByPeriode(@Param("d") LocalDateTime debut, @Param("f") LocalDateTime fin);

    @Query("SELECT p.poste, SUM(p.the), SUM(p.thc), SUM(p.hm) FROM Production p " +
            "WHERE p.dateLocale BETWEEN :d AND :f GROUP BY p.poste ORDER BY p.poste")
    List<Object[]> statsByPosteAndPeriode(
            @Param("d") LocalDateTime debut, @Param("f") LocalDateTime fin);
}