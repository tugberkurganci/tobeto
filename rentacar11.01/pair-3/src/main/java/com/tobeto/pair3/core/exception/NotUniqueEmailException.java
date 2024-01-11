package com.tobeto.pair3.core.exception;

import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Collections;
import java.util.Map;

public class NotUniqueEmailException extends RuntimeException{
    public NotUniqueEmailException() {
        super("Validation error");

    }
    public Map<String,String> getValidationErros(){
        return Collections.singletonMap("email","E-mail in use");
    }

}