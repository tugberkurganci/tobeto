package com.tobeto.pair3.core.exception;

import org.springframework.context.i18n.LocaleContextHolder;

public class ActivationNotificationException extends RuntimeException {

    public ActivationNotificationException(){
        super("activation error");
    }


}