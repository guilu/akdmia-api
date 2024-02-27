package com.diegobarrioh.api.akdmiaapi.exception;

public class UnitNotFoundException extends RuntimeException {
    public UnitNotFoundException(Long id) {
        super("Could not find unit " + id);
    }
}