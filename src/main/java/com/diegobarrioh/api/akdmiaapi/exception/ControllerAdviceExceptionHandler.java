package com.diegobarrioh.api.akdmiaapi.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {

    @ResponseBody
    @ExceptionHandler({UnitNotFoundException.class,GroupNotFoundException.class, AnswerNotFoundException.class,QuestionNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorInfo unitNotFoundHandler(HttpServletRequest req,RuntimeException ex) {
        return new ErrorInfo(req.getRequestURI(),ex);
    }

    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ErrorInfo IllegalArgumentHandler(HttpServletRequest req, RuntimeException ex) {
        return new ErrorInfo(req.getRequestURI(),ex);
    }
}
