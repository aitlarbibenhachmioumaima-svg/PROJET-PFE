package com.ocp.production_epierrage.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.ocp.production_epierrage.dto.UtilisateurRequestDTO;
import com.ocp.production_epierrage.dto.UtilisateurResponseDTO;
import com.ocp.production_epierrage.services.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin/utilisateurs")
@RequiredArgsConstructor
@Tag(name = "Admin — Gérer utilisateurs + Valider inscriptions")
@SecurityRequirement(name = "Bearer")
public class AdminController {

    private final UtilisateurService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Créer un utilisateur avec rôle(s)")
    public UtilisateurResponseDTO creer(@Valid @RequestBody UtilisateurRequestDTO req) {
        return service.creer(req);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Lister tous les utilisateurs actifs")
    public List<UtilisateurResponseDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UtilisateurResponseDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Modifier un utilisateur")
    public UtilisateurResponseDTO modifier(@PathVariable Long id,
                                           @Valid @RequestBody UtilisateurRequestDTO req) {
        return service.modifier(id, req);
    }

    @PatchMapping("/{id}/valider")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Valider l'inscription — actif = true")
    public void valider(@PathVariable Long id) { service.validerInscription(id); }

    @PatchMapping("/{id}/desactiver")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Désactiver un compte")
    public void desactiver(@PathVariable Long id) { service.desactiver(id); }
}
