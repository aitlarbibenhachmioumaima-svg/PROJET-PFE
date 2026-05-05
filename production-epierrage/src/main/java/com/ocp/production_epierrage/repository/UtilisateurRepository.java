package com.ocp.production_epierrage.repository;


import com.ocp.production_epierrage.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Optional<Utilisateur> findByLogin(String login);
    boolean existsByLogin(String login);
    boolean existsByEmail(String email);
    List<Utilisateur> findByActifTrue();

    @Query("SELECT u FROM Utilisateur u JOIN u.roles r WHERE r.code = :roleCode")
    List<Utilisateur> findByRoleCode(@Param("roleCode") String roleCode);
}