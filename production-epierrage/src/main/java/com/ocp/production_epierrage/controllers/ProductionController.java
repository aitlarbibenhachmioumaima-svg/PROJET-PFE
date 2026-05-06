package com.ocp.production_epierrage.controllers;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.ocp.production_epierrage.dto.ProductionRequestDTO;
import com.ocp.production_epierrage.dto.ProductionResponseDTO;
import com.ocp.production_epierrage.services.ProductionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/productions")
@RequiredArgsConstructor
@Tag(name = "Production — Saisir, Modifier, Clôturer, Valider")
@SecurityRequirement(name = "Bearer")
public class ProductionController {

    private final ProductionService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ADMIN','CDP','OPE')")
    @Operation(summary = "Saisir une production — Opérateur")
    public ProductionResponseDTO saisir(@Valid @RequestBody ProductionRequestDTO req,
                                        @AuthenticationPrincipal UserDetails user) {
        return service.saisir(req, user.getUsername());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','CDP','OPE')")
    @Operation(summary = "Modifier sa saisie — Opérateur")
    public ProductionResponseDTO modifier(@PathVariable Long id,
                                          @Valid @RequestBody ProductionRequestDTO req,
                                          @AuthenticationPrincipal UserDetails user) {
        return service.modifier(id, req, user.getUsername());
    }

    @PatchMapping("/{id}/cloturer")
    @PreAuthorize("hasAnyRole('ADMIN','CDP','OPE')")
    @Operation(summary = "Clôturer → TERMINER — Opérateur")
    public ProductionResponseDTO cloturer(@PathVariable Long id,
                                          @AuthenticationPrincipal UserDetails user) {
        return service.cloturer(id, user.getUsername());
    }

    @PatchMapping("/{id}/valider")
    @PreAuthorize("hasAnyRole('ADMIN','CDP')")
    @Operation(summary = "Valider → VALIDER — Chef de poste")
    public ProductionResponseDTO valider(@PathVariable Long id) {
        return service.valider(id);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','CDP','OPE','RESP')")
    @Operation(summary = "Liste sur une période")
    public List<ProductionResponseDTO> findByPeriode(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime debut,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return service.findByPeriode(debut, fin);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','CDP','OPE','RESP')")
    public ProductionResponseDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('ADMIN','CDP')")
    @Operation(summary = "Supprimer — Chef de poste")
    public void supprimer(@PathVariable Long id) { service.supprimer(id); }
}