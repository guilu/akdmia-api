package com.diegobarrioh.api.akdmiaapi.controller;

import com.diegobarrioh.api.akdmiaapi.exception.GroupNotFoundException;
import com.diegobarrioh.api.akdmiaapi.domain.entity.Group;
import com.diegobarrioh.api.akdmiaapi.domain.repository.GroupRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.annotations.OpenAPI31;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1",produces = MediaType.APPLICATION_JSON_VALUE)
@Tag( name = "Group", description = "Endpoints to manage Group entities")
public class GroupController {

    private final GroupRepository groupRepository;

    public GroupController(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }


    @GetMapping("/groups")
    @Operation(
            summary = "Get all groups",
            description = "Return all elements in the group table",
            tags = { "Group" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Group.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    List<Group> all(){
        return groupRepository.findAll();
    }

    @PostMapping("/groups")
    @Operation(
            summary = "Add a group",
            description = "Creates a new group from request.",
            tags = { "Group" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Group.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    Group newGroup(@RequestBody Group newGroup) {
        return groupRepository.save(newGroup);
    }

    @GetMapping("/groups/{id}")
    @Operation(
            summary = "Find a group",
            description = "Finds a group by their Id.",
            tags = { "Group" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Group.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    Group one(@Parameter(description = "The Id of the group to find.")  @PathVariable Long id) {
        return groupRepository.findById(id)
                .orElseThrow( () -> new GroupNotFoundException(id) );
    }

    //put one
    @PutMapping("/groups/{id}")
    @Operation(
            summary = "Replace a group",
            description = "Finds a group by their Id and replaces it with the one in the request.",
            tags = { "Group" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Group.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    Group replaceGroup( @Parameter(description = "The values of the new group") @RequestBody Group newGroup,
                        @Parameter(description = "The Id of the group to be replaced") @PathVariable Long id) {
        return groupRepository.findById(id)
                .map( group -> {
                    group.setText( newGroup.getText());
                    group.setUnits( newGroup.getUnits());
                    return groupRepository.save(group);
                })
                .orElseGet( () -> {
                    newGroup.setId(id);
                    return groupRepository.save(newGroup);
                    });
    }

    //delete one
    @DeleteMapping("/groups/{id}")
    @Operation(
            summary = "Delete a group",
            description = "Delete a group by their id.",
            tags = { "Group" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Group.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    void deleteGroup(@Parameter(description = "The id of the group to be deleted") @PathVariable Long id){
        groupRepository.deleteById(id);
    }

}
