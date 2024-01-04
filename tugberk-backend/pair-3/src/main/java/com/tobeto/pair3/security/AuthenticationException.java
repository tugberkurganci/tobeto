package com.tobeto.pair3.security;

import org.springframework.context.i18n.LocaleContextHolder;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException() {
        super("AuthenticationException");
    }
}

