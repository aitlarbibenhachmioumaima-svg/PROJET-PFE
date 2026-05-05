package com.ocp.production_epierrage.repository;


import com.ocp.production_epierrage.entity.KPIType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface KPITypeRepository extends JpaRepository<KPIType, Long> {
    Optional<KPIType> findByCode(String code);
}