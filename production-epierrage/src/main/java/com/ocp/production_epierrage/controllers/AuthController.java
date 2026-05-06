package com.ocp.production_epierrage.controllers;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.ocp.production_epierrage.dto.ChangePasswordRequestDTO;
import com.ocp.production_epierrage.dto.LoginRequestDTO;
import com.ocp.production_epierrage.dto.AuthResponseDTO;
import com.ocp.production_epierrage.services.AuthService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentification")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "S'authentifier — tous les acteurs")
    public AuthResponseDTO login(@Valid @RequestBody LoginRequestDTO req) {
        return authService.login(req);
    }

    @PatchMapping("/change-password")
    @Operation(summary = "Changer son mot de passe")
    public void changePassword(@AuthenticationPrincipal UserDetails user,
                               @Valid @RequestBody ChangePasswordRequestDTO req) {
        authService.changePassword(user.getUsername(), req);
    }
}