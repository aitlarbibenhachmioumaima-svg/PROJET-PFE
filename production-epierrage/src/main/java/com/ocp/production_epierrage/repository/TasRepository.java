package com.ocp.production_epierrage.repository;


import com.ocp.production_epierrage.entity.Tas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TasRepository extends JpaRepository<Tas, Long> {
    List<Tas> findByZone(String zone);
    List<Tas> findByStatutCouleur(String statutCouleur);
    List<Tas> findByNumeroDuTas(Integer numeroDuTas);
}