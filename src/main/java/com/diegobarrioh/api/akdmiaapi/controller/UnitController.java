package com.diegobarrioh.api.akdmiaapi.controller;

import com.diegobarrioh.api.akdmiaapi.domain.entity.Group;
import com.diegobarrioh.api.akdmiaapi.exception.UnitNotFoundException;
import com.diegobarrioh.api.akdmiaapi.domain.entity.Unit;
import com.diegobarrioh.api.akdmiaapi.domain.repository.UnitRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import io.swagger.v3.oas.models.annotations.OpenAPI31;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1",produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Unit", description = "Endpoints to manage Unit entities")
public class UnitController {

    private final UnitRepository unitRepository;

    public UnitController(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/units")
    @Operation(
            summary = "Get all units",
            description = "Return all elements in the unit table",
            tags = { "Unit" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Unit.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    List<Unit> all() {
        return unitRepository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/units")
    @Operation(
            summary = "Add a units",
            description = "Create a new unit from request data.",
            tags = { "Unit" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Unit.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    Unit newUnit(@Parameter(description = "The new unit to be added") @RequestBody Unit newUnit) {
        return unitRepository.save(newUnit);
    }

    @GetMapping("/units/{id}")
    @Operation(
            summary = "Find a unit",
            description = "Finds a unit by their id.",
            tags = { "Unit" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Unit.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    Unit one(@Parameter(description = "The unit id you are looking for", required = true) @PathVariable Long id) {
        return unitRepository.findById(id)
                .orElseThrow(() -> new UnitNotFoundException(id));
    }

    @PutMapping("/units/{id}")
    @Operation(
            summary = "Replace a unit",
            description = "Update an existing unit with the info in the request",
            tags = { "Unit" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Unit.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    Unit replaceUnit(@Parameter(description = "The new info of the unit") @RequestBody Unit newUnit,
                     @Parameter(description = "The id of the unit to update") @PathVariable Long id) {

        return unitRepository.findById(id)
                .map(unit -> {
                    unit.setText(newUnit.getText());
                    unit.setGroup(newUnit.getGroup());
                    unit.setQuestions(newUnit.getQuestions());
                    return unitRepository.save(unit);
                })
                .orElseGet(() -> {
                    newUnit.setId(id);
                    return unitRepository.save(newUnit);
                });
    }

    @DeleteMapping("/unit/{id}")
    @Operation(
            summary = "Delete a unit",
            description = "Deletes a unit by their id",
            tags = { "Unit" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Unit.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    void deleteUnit(@Parameter(description = "The id of the unit to be deleted") @PathVariable Long id) {
        unitRepository.deleteById(id);
    }
}
