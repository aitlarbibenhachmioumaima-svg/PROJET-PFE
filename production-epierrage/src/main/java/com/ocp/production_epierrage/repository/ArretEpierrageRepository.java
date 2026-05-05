package com.ocp.production_epierrage.repository;


import com.ocp.production_epierrage.entity.ArretEpierrage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ArretEpierrageRepository extends JpaRepository<ArretEpierrage, Long> {
    List<ArretEpierrage> findBySemaine(String semaine);
    List<ArretEpierrage> findByPoste(Integer poste);
    List<ArretEpierrage> findByChaine(String chaine);
    List<ArretEpierrage> findByAtelier(String atelier);
}