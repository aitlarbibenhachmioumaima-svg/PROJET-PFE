package com.ocp.production_epierrage.repository;


import com.ocp.production_epierrage.entity.ArretRouePelle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ArretRouePelleRepository extends JpaRepository<ArretRouePelle, Long> {
    List<ArretRouePelle> findByCouche(String couche);
    List<ArretRouePelle> findByAtelier(String atelier);
}