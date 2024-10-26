package com.diegobarrioh.api.akdmiaapi.controller.v1;

import com.diegobarrioh.api.akdmiaapi.domain.entity.Answer;
import com.diegobarrioh.api.akdmiaapi.domain.repository.AnswerRepository;
import com.diegobarrioh.api.akdmiaapi.exception.AnswerNotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1",produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Answer", description = "Endpoints to manage Answer entities")
@AllArgsConstructor
public class AnswerController {

    protected final AnswerRepository answerRepository;

    @GetMapping("/answers")
    List<Answer> all(@RequestParam(value = "q",required = false) String search){
        if (search == null || search.isEmpty()) {
            return answerRepository.findAll();
        }

        return answerRepository.findByTextContainingIgnoreCase(search);

    }

    @GetMapping("/answers/{id}")
    Answer one(@PathVariable Long id) {
        return answerRepository.findById(id)
                .orElseThrow( () -> new AnswerNotFoundException(id) );
    }

    @PostMapping("/answers")
    Answer newAnswer(@RequestBody Answer newAnswer){
        return answerRepository.save(newAnswer);
    }

}
