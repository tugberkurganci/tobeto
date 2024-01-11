package com.tobeto.pair3.security;

import com.tobeto.pair3.security.token.Token;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthResponse {


    private UserResponse user;
    private Token token;

}
