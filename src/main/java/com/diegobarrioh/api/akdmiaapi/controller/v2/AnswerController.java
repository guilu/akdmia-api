package com.diegobarrioh.api.akdmiaapi.controller.v2;

import com.diegobarrioh.api.akdmiaapi.domain.entity.Answer;
import com.diegobarrioh.api.akdmiaapi.domain.repository.AnswerRepository;
import com.diegobarrioh.api.akdmiaapi.exception.AnswerNotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("AnswerControllerv2")
@RequestMapping(value = "api/v2",produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Answer", description = "Endpoints to manage Answer entities")
public class AnswerController {

    private final AnswerRepository answerRepository;
    public AnswerController(AnswerRepository answerRepository)
    {
        this.answerRepository = answerRepository;
    }

    @GetMapping("/answers")
    List<Answer> all(@RequestParam(value = "q",required = false) String search){
        if (search == null || search.isEmpty()) {
            return answerRepository.findAll();
        }

        return answerRepository.findByTextContainingIgnoreCase(search);

    }

    @PostMapping("/answers")
    Answer newAnswer(@RequestBody Answer newAnswer){
        return answerRepository.save(newAnswer);
    }

    @GetMapping("/answers/{id}")
    Answer one(@PathVariable Long id) {
        return answerRepository.findById(id)
                .orElseThrow( () -> new AnswerNotFoundException(id) );
    }

    @PutMapping("/answers/{id}")
    Answer replaceAnswer(@RequestBody Answer newAnswer, @PathVariable Long id) {

        return answerRepository.findById(id).map(
                unit -> {
                    unit.setText(newAnswer.getText());
                    unit.setOk(newAnswer.isOk());
                    unit.setQuestion(newAnswer.getQuestion());
                    return answerRepository.save(unit);
                }
        ).orElseGet(() -> {
                    newAnswer.setId(id);
                    return answerRepository.save(newAnswer);
                });
    }


    @DeleteMapping("/answers/{id}")
    void deleteAnswer(@PathVariable Long id) {
        answerRepository.deleteById(id);
    }

}
