package com.ocp.production_epierrage.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.Set;

public record UtilisateurRequestDTO(
        @NotBlank(message = "Login obligatoire") String login,
        @NotBlank(message = "Nom obligatoire") String nom,
        String prenom,
        @Email String email,
        @NotEmpty(message = "Au moins un rôle requis") Set<String> roleCodes
) {}