package com.ocp.production_epierrage.repository;


import com.ocp.production_epierrage.entity.Arret;
import com.ocp.production_epierrage.entity.TypeArret;
import com.ocp.production_epierrage.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ArretRepository extends JpaRepository<Arret, Long> {

    List<Arret> findByDateDebutBetweenOrderByDateDebutAsc(
            LocalDateTime debut, LocalDateTime fin);

    List<Arret> findByType(TypeArret type);

    List<Arret> findByUtilisateur(Utilisateur u);

    @Query("SELECT SUM(a.duree) FROM Arret a " +
            "WHERE a.dateDebut BETWEEN :d AND :f AND a.type.code = :code")
    Double sumDureeByTypeAndPeriode(
            @Param("d") LocalDateTime debut,
            @Param("f") LocalDateTime fin,
            @Param("code") String typeCode);

    @Query("SELECT COUNT(a) FROM Arret a WHERE a.dateDebut BETWEEN :d AND :f")
    Long countByPeriode(@Param("d") LocalDateTime debut, @Param("f") LocalDateTime fin);

    @Query("SELECT a.equipement, COUNT(a), SUM(a.duree) FROM Arret a " +
            "WHERE a.dateDebut BETWEEN :d AND :f " +
            "GROUP BY a.equipement ORDER BY SUM(a.duree) DESC")
    List<Object[]> topDefaillances(
            @Param("d") LocalDateTime debut, @Param("f") LocalDateTime fin);
}