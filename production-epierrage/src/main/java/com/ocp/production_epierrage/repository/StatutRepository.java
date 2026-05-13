package com.ocp.production_epierrage.repository;


import com.ocp.production_epierrage.entity.Statut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StatutRepository extends JpaRepository<Statut, Long> {
    Optional<Statut> findByCode(String code);
    boolean existsByCode(String code);
}