package com.diegobarrioh.api.akdmiaapi.domain.entity;

import com.diegobarrioh.api.akdmiaapi.domain.BaseEntity;
import com.diegobarrioh.api.akdmiaapi.domain.DomainModelNames;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.util.List;

@Entity
@Table(name = DomainModelNames.TB05_EXAM)
public class Exam extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 5L;

    @Size(max = 1024)
    @Column
    private String text;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Question> questions;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
