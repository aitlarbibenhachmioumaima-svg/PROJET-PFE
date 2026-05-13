package com.ocp.production_epierrage.controllers;





import com.ocp.production_epierrage.dto.ProductionRequestDTO;
import com.ocp.production_epierrage.dto.ProductionResponseDTO;
import com.ocp.production_epierrage.services.ProductionService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController

@RequestMapping("/api/productions")

@CrossOrigin("*")

public class ProductionController {

    private final ProductionService productionService;

    public ProductionController(
            ProductionService productionService
    ) {

        this.productionService = productionService;
    }

    @PostMapping

    @ResponseStatus(HttpStatus.CREATED)

    public ProductionResponseDTO addProduction(

            @Valid
            @RequestBody
            ProductionRequestDTO dto
    ) {

        return productionService
                .addProduction(dto);
    }

    @GetMapping

    public List<ProductionResponseDTO>
    getAllProductions() {

        return productionService
                .getAllProductions();
    }

    @GetMapping("/{id}")

    public ProductionResponseDTO
    getProductionById(

            @PathVariable Long id
    ) {

        return productionService
                .getProductionById(id);
    }

    @PutMapping("/{id}")

    public ProductionResponseDTO
    updateProduction(

            @PathVariable Long id,

            @Valid
            @RequestBody
            ProductionRequestDTO dto
    ) {

        return productionService
                .updateProduction(id, dto);
    }

    @DeleteMapping("/{id}")

    @ResponseStatus(HttpStatus.NO_CONTENT)

    public void deleteProduction(
            @PathVariable Long id
    ) {

        productionService
                .deleteProduction(id);
    }

    @GetMapping("/date")

    public List<ProductionResponseDTO>
    getByDate(

            @RequestParam LocalDate date
    ) {

        return productionService
                .getByDate(date);
    }
}