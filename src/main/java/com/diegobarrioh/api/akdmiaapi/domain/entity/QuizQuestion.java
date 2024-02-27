package com.diegobarrioh.api.akdmiaapi.domain.entity;

import com.diegobarrioh.api.akdmiaapi.domain.BaseEntity;
import com.diegobarrioh.api.akdmiaapi.domain.DomainModelNames;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = DomainModelNames.TB07_QUESTION_QUIZ)
public class QuizQuestion extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = DomainModelNames.QUIZ_ID, referencedColumnName = DomainModelNames.ID)
    @JsonIgnore
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = DomainModelNames.QUESTION_ID, referencedColumnName = DomainModelNames.ID)
    @JsonIgnore
    private Question question;

    private boolean correct;

    public QuizQuestion() {

    }

    public QuizQuestion(Question question) {
        this.question = question;
        this.correct = false;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public String toString() {
        String json = question.toString();
        return "{" + json + "}";
    }

}
