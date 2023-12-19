package com.diegobarrioh.api.akdmiaapi.domain.repository;

import com.diegobarrioh.api.akdmiaapi.domain.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
}