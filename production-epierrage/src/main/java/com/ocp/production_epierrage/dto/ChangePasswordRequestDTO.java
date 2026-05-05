package com.ocp.production_epierrage.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChangePasswordRequestDTO(
        @NotBlank String ancienPassword,
        @NotBlank @Size(min = 6) String nouveauPassword
) {}