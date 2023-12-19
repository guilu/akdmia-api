package com.diegobarrioh.api.akdmiaapi.domain.entity;

import com.diegobarrioh.api.akdmiaapi.domain.BaseEntity;
import com.diegobarrioh.api.akdmiaapi.domain.DomainModelNames;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = DomainModelNames.TB06_QUIZ)
public class Quiz extends BaseEntity {

    private boolean ended;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<QuizQuestion> quizQuestions;

    private int correct;
    private int failed;

    private int numQuestionsByUnit;
    private int numQuestionsTotal;

    public Quiz(){
    }

    public Quiz(int numQuestionsByUnit, int numQuestionsTotal){
        this.correct=0;
        this.failed=0;
        this.numQuestionsByUnit = numQuestionsByUnit;
        this.numQuestionsTotal = numQuestionsTotal;
    }

    public void setQuizQuestions(List<Question> questions){
        if (questions == null ){
            return;
        }
        this.quizQuestions = new ArrayList<>();
        for (Question question: questions) {
            QuizQuestion quizQuestion = new QuizQuestion(question);
            quizQuestion.setQuiz(this);
            this.quizQuestions.add(quizQuestion);
        }
    }

    public String toString(){
        return "A:"+this.correct+" F:"+this.failed+" PxT:"+this.numQuestionsByUnit+" PT:"+this.numQuestionsTotal;
    }

    public String toJson() {

        StringBuffer test = new StringBuffer();

        for (QuizQuestion question : quizQuestions) {
            test.append(question.toString());
        }
        return test.toString();

    }

}
