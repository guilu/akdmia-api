package com.diegobarrioh.api.akdmiaapi.controller;

import com.diegobarrioh.api.akdmiaapi.exception.GroupNotFoundException;
import com.diegobarrioh.api.akdmiaapi.domain.entity.Group;
import com.diegobarrioh.api.akdmiaapi.domain.repository.GroupRepository;
import io.swagger.v3.oas.models.annotations.OpenAPI31;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupController {

    private final GroupRepository groupRepository;

    public GroupController(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @GetMapping("/groups")
    List<Group> all(){
        return groupRepository.findAll();
    }

    @PostMapping("/groups")
    Group newGroup(@RequestBody Group newGroup) {
        return groupRepository.save(newGroup);
    }

    //get one
    @GetMapping("/groups/{id}")
    Group one(@PathVariable Long id) {
        return groupRepository.findById(id)
                .orElseThrow( () -> new GroupNotFoundException(id) );
    }

    //put one
    @PutMapping("/groups/{id}")
    Group replaceGroup(@RequestBody Group newGroup, @PathVariable Long id) {
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
    void deleteGroup(@PathVariable Long id){
        groupRepository.deleteById(id);
    }

}
