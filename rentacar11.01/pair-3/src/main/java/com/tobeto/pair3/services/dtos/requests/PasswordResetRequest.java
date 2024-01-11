package com.tobeto.pair3.services.dtos.requests;

import jakarta.validation.constraints.Email;

public record PasswordResetRequest(@Email String email) {

}

