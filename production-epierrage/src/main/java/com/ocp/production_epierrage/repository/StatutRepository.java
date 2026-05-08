package com.ocp.production_epierrage.repository;


import com.ocp.production_epierrage.entity.StatutEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StatutRepository extends JpaRepository<StatutEnum, Long> {
    Optional<StatutEnum> findByCode(String code);
    boolean existsByCode(String code);
}