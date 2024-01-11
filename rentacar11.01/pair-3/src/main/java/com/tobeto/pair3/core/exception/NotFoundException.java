package com.tobeto.pair3.core.exception;

import org.springframework.context.i18n.LocaleContextHolder;

public class NotFoundException extends RuntimeException {

    public NotFoundException(int id){
        super("user not found"+ id);
    }
}