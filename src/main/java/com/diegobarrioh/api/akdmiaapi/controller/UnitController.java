package com.diegobarrioh.api.akdmiaapi.controller;

import com.diegobarrioh.api.akdmiaapi.exception.UnitNotFoundException;
import com.diegobarrioh.api.akdmiaapi.domain.entity.Unit;
import com.diegobarrioh.api.akdmiaapi.domain.repository.UnitRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1",produces = MediaType.APPLICATION_JSON_VALUE)
public class UnitController {

    private final UnitRepository unitRepository;

    public UnitController(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/units")
    List<Unit> all() {
        return unitRepository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/units")
    Unit newUnit(@RequestBody Unit newUnit) {
        return unitRepository.save(newUnit);
    }

    @GetMapping("/units/{id}")
    Unit one(@PathVariable Long id) {
        return unitRepository.findById(id)
                .orElseThrow(() -> new UnitNotFoundException(id));
    }

    @PutMapping("/units/{id}")
    Unit replaceUnit(@RequestBody Unit newUnit, @PathVariable Long id) {

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
    void deleteUnit(@PathVariable Long id) {
        unitRepository.deleteById(id);
    }
}
