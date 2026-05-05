package com.ocp.production_epierrage.dto;

import java.util.Set;

public record AuthResponseDTO(
        String token,
        String login,
        String nom,
        Set<String> roles,
        Long expiresIn
) {}