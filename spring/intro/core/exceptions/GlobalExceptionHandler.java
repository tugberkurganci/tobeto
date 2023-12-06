package com.tobeto.a.spring.intro.core.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {


  @ExceptionHandler({MethodArgumentNotValidException.class, RuntimeException.class, Exception.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ProblemDetails handleException(Exception ex) {
        if (ex instanceof MethodArgumentNotValidException) {
            // Validation exception handling
            ValidationProblemDetails validationProblemDetails = new ValidationProblemDetails();
            validationProblemDetails.setMessage("VALIDATION.EXCEPTION");
            validationProblemDetails.setValidationErrors(new HashMap<>());

            for (FieldError fieldError : ((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors()) {
                validationProblemDetails.getValidationErrors().put(fieldError.getField(), fieldError.getDefaultMessage());
            }

            return validationProblemDetails;
        } else if (ex instanceof RuntimeException) {
            // Business exception handling
            ProblemDetails problemDetails = new ProblemDetails();
            problemDetails.setMessage(ex.getMessage());
            return problemDetails;
        } else {
            // General exception handling
            String errorMessage = "An error occurred: " + ex.getMessage();
            return new ProblemDetails(errorMessage);
        }
    }
}
