package com.diegobarrioh.api.akdmiaapi.exception;

public class QuestionNotFoundException extends RuntimeException {
    public QuestionNotFoundException(Long id) {
        super("Could not fine question with "+ id);
    }
}
