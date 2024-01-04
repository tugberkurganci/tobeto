package com.tobeto.pair3.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@NoArgsConstructor
public class AuthEntryPoint  implements AuthenticationEntryPoint {


    @Qualifier("handlerExceptionResolver")
    @Autowired
    private HandlerExceptionResolver exceptionResolver;



    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        exceptionResolver.resolveException(request,response,null,authException);
    }
}