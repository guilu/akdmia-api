package com.diegobarrioh.api.akdmiaapi.domain.repository;

import com.diegobarrioh.api.akdmiaapi.domain.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findQuestionsByTextContainingIgnoreCase(String search);
}