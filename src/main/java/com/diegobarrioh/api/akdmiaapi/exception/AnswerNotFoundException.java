package com.diegobarrioh.api.akdmiaapi.exception;

public class AnswerNotFoundException extends RuntimeException {
    public AnswerNotFoundException(Long id) {
        super("Could not found answer " + id);
    }
}
