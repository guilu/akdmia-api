package com.diegobarrioh.api.akdmiaapi.domain.entity;

import com.diegobarrioh.api.akdmiaapi.domain.BaseEntity;
import com.diegobarrioh.api.akdmiaapi.domain.DomainModelNames;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = DomainModelNames.TB02_UNIT)
public class Unit extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 2L;

    @ManyToOne
    @JoinColumn(name = DomainModelNames.GROUP_ID, referencedColumnName = DomainModelNames.ID)
    @JsonIgnore
    private Group group;

    @Size(max = 1024)
    private String text;

    @OneToMany(mappedBy = "unit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Question> questions;

    public Unit(){}

    public Unit(String text) {
        this.text = text;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

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

    public int getNumQuestions() {
        return (this.questions != null) ? this.questions.size() : 0;
    }

    /**
     * @param numQuestions
     * @return
     */
    public List<Question> getQuestionsRandom(int numQuestions) {
        //si tiene mas questions que numQuestions
        //desordeno y saco numQuestions
        if (this.questions.size() > numQuestions) {
            Collections.shuffle(this.getQuestions());
            return this.getQuestions().subList(0, numQuestions);
        } else {
            Collections.shuffle(this.getQuestions());
            return this.getQuestions();
        }
    }

    /**
     * @return
     */
    public boolean hasQuestions() {
        return (!this.getQuestions().isEmpty());
    }
}
