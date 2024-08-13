package com.diegobarrioh.api.akdmiaapi.domain.entity;

import com.diegobarrioh.api.akdmiaapi.domain.BaseEntity;
import com.diegobarrioh.api.akdmiaapi.domain.DomainModelNames;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.util.List;

@Entity
@Table(name = DomainModelNames.TB03_QUESTION)
public class Question extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 3L;

    @ManyToOne
    @JoinColumn(name = DomainModelNames.UNIT_ID, referencedColumnName = DomainModelNames.ID)
    @JsonIgnore
    private Unit unit;

    @Size(max = 1024)
    private String text;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Answer> answers;

    @ManyToOne
    @JoinColumn(name = DomainModelNames.EXAM_ID, referencedColumnName = DomainModelNames.ID)
    @JsonIgnore
    private Exam exam;


    public Question() {

    }

    public Question(String text) {
        this.text = text;
    }


    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public String toString() {
        StringBuilder json = new StringBuilder(" Question:" + this.getId() + ", Unit:" + this.getUnit().getId());
        for (Answer answer : answers) {
            json.append(answer.toString());
        }
        return "{" + json + "}";
    }
}
