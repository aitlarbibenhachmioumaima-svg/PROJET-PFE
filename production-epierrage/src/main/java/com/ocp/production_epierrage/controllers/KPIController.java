package com.ocp.production_epierrage.controllers;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.ocp.production_epierrage.dto.KPIRequestDTO;
import com.ocp.production_epierrage.dto.KPIGraphResponseDTO;
import com.ocp.production_epierrage.dto.KPIResponseDTO;
import com.ocp.production_epierrage.services.KPIService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/kpi")
@RequiredArgsConstructor
@Tag(name = "KPI — Tableau + Graphe + Export")
@SecurityRequirement(name = "Bearer")
public class KPIController {

    private final KPIService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ADMIN','RESP')")
    @Operation(summary = "Saisir un KPI journalier")
    public KPIResponseDTO saisir(@Valid @RequestBody KPIRequestDTO req,
                                 @AuthenticationPrincipal UserDetails user) {
        return service.saisir(req, user.getUsername());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','RESP')")
    public KPIResponseDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/tableau")
    @PreAuthorize("hasAnyRole('ADMIN','RESP')")
    @Operation(summary = "Tableau KPI — données brutes comme Excel image 6")
    public List<KPIResponseDTO> tableau(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate debut,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        return service.findByPeriode(debut, fin);
    }

    @GetMapping("/graphe")
    @PreAuthorize("hasAnyRole('ADMIN','RESP')")
    @Operation(summary = "Données graphe — labels + séries pour Chart.js/Recharts")
    public KPIGraphResponseDTO graphe(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate debut,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        return service.getGraphData(debut, fin);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void supprimer(@PathVariable Long id) { service.supprimer(id); }
}