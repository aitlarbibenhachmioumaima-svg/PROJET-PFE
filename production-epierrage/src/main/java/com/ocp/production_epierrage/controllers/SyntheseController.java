package com.ocp.production_epierrage.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.ocp.production_epierrage.dto.request.SyntheseProductionRequestDTO;
import com.ocp.production_epierrage.dto.response.*;
import com.ocp.production_epierrage.services.SyntheseProductionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/synthese")
@RequiredArgsConstructor
@Tag(name = "Synthèse Production — Stats + Graphe")
@SecurityRequirement(name = "Bearer")
public class SyntheseController {

    private final SyntheseProductionService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ADMIN','CDP','RESP')")
    @Operation(summary = "Saisir une synthèse journalière")
    public SyntheseResponseDTO saisir(@Valid @RequestBody SyntheseProductionRequestDTO req) {
        return service.saisir(req);
    }

    @GetMapping("/date/{date}")
    @PreAuthorize("hasAnyRole('ADMIN','CDP','OPE','RESP')")
    @Operation(summary = "Synthèse d'une date")
    public SyntheseResponseDTO findByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return service.findByDate(date);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','CDP','OPE','RESP')")
    @Operation(summary = "Synthèses sur une période — comme tableau image 4")
    public List<SyntheseResponseDTO> findByPeriode(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate debut,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        return service.findByPeriode(debut, fin);
    }

    @GetMapping("/stats")
    @PreAuthorize("hasAnyRole('ADMIN','RESP')")
    @Operation(summary = "Statistiques — moyennes, totaux, min/max, séries graphe par poste")
    public SyntheseStatsResponseDTO getStats(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate debut,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        return service.getStats(debut, fin);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void supprimer(@PathVariable Long id) { service.supprimer(id); }
}