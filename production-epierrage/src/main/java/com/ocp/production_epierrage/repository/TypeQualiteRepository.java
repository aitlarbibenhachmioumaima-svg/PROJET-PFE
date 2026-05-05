package com.ocp.production_epierrage.repository;



import com.ocp.production_epierrage.entity.TypeQualite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TypeQualiteRepository extends JpaRepository<TypeQualite, Long> {
    Optional<TypeQualite> findByLibelle(String libelle);
    Optional<TypeQualite> findByCodeCouleur(String codeCouleur);
}