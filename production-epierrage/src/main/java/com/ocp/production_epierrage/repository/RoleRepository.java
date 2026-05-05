package com.ocp.production_epierrage.repository;


import com.ocp.production_epierrage.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByCode(String code);
    Optional<Role> findByLibelle(String libelle);
    boolean existsByCode(String code);
}