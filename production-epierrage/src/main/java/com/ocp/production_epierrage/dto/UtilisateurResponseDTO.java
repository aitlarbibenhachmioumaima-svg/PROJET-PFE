package com.ocp.production_epierrage.dto;
import java.time.LocalDateTime;
import java.util.Set;

public record UtilisateurResponseDTO(
        Long id,
        String login,
        String nom,
        String prenom,
        String email,
        Boolean actif,
        LocalDateTime dateCreation,
        Set<String> roles,
        String motDePasseTemporaire
) {}
