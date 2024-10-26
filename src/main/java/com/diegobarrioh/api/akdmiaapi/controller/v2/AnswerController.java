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
public class AnswerController extends com.diegobarrioh.api.akdmiaapi.controller.v1.AnswerController {

    public AnswerController(AnswerRepository answerRepository) {
        super(answerRepository);
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
