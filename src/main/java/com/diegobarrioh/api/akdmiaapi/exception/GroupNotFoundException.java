package com.diegobarrioh.api.akdmiaapi.exception;

public class GroupNotFoundException extends RuntimeException{
    public GroupNotFoundException(Long id) {
        super("Could not found group " + id);
    }
}
