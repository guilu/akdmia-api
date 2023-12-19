package com.diegobarrioh.api.akdmiaapi.domain.entity;

import com.diegobarrioh.api.akdmiaapi.domain.BaseEntity;
import com.diegobarrioh.api.akdmiaapi.domain.DomainModelNames;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.io.Serial;

@Entity
@Table(name = DomainModelNames.TB04_ANSWER)
public class Answer extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 4L;

    @ManyToOne
    @JoinColumn(name = DomainModelNames.QUESTION_ID, referencedColumnName = DomainModelNames.ID)
    @JsonIgnore
    private Question question;

    @Size(max = 1024)
    @Column
    private String text;
    @Column
    private boolean ok;

    public Answer(){

    }
    public Answer(String text, boolean ok, Question question){
        this.text = text;
        this.ok = ok;
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getTexto() {
        return text;
    }

    public void setTexto(String text) {
        this.text = text;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }


    public String toString() {
        return "{ id:"+ this.getId() + ",correcta:" + this.isOk() + "}";
    }

}
