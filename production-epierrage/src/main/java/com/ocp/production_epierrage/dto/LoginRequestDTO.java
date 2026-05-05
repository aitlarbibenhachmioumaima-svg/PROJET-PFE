package com.ocp.production_epierrage.dto;


import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank(message = "Le login est obligatoire") String login,
        @NotBlank(message = "Le mot de passe est obligatoire") String password
) {}