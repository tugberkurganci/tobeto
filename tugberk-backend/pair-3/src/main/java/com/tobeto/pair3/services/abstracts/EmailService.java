package com.tobeto.pair3.services.abstracts;

public interface EmailService {
    void sendActivationEmail(String email,String activationToken);
    void sendReCreatePassword(String email, String passToken);
}
