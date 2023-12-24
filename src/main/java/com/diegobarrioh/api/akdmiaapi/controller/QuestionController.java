package com.diegobarrioh.api.akdmiaapi.controller;

import com.diegobarrioh.api.akdmiaapi.domain.entity.Question;
import com.diegobarrioh.api.akdmiaapi.domain.repository.QuestionRepository;
import com.diegobarrioh.api.akdmiaapi.exception.QuestionNotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1",produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Question", description = "Endpoints to manage Question entities")
public class QuestionController {

    private final QuestionRepository questionRepository;

    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @GetMapping("/questions")
    List<Question> all(){
        return questionRepository.findAll();
    }

    @PostMapping("/questions")
    Question newQuestion(@RequestBody Question newQuestion) {
        return questionRepository.save(newQuestion);
    }

    @GetMapping("/questions/{id}")
    Question one(@PathVariable Long id) {
        return questionRepository.findById(id)
                .orElseThrow( () -> new QuestionNotFoundException(id));
    }


    @PutMapping("/questions/{id}")
    Question replaceQuestion(@RequestBody Question newQuestion, @PathVariable Long id) {
        return questionRepository
                .findById(id)
                .map( question -> {
                    question.setText(newQuestion.getText());
                    question.setAnswers(newQuestion.getAnswers());
                    question.setExam(newQuestion.getExam());
                    question.setUnit(newQuestion.getUnit());
                    return questionRepository.save(question);
                })
                .orElseGet( () -> {
                    newQuestion.setId(id);
                    return questionRepository.save(newQuestion);
                });
    }

    @DeleteMapping("/questions/{id}")
    void deleteQuestion(@PathVariable Long id) {
        questionRepository.deleteById(id);
    }




}
