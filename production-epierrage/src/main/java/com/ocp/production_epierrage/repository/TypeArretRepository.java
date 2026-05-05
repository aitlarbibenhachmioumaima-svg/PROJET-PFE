package com.ocp.production_epierrage.repository;


import com.ocp.production_epierrage.entity.TypeArret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TypeArretRepository extends JpaRepository<TypeArret, Long> {
    Optional<TypeArret> findByCode(String code);
}