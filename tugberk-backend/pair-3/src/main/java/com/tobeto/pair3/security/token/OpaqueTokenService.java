package com.tobeto.pair3.security.token;


import com.tobeto.pair3.entities.User;
import com.tobeto.pair3.security.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Primary
public class OpaqueTokenService  implements TokenService{

    @Autowired
    TokenRepository tokenRepository;
    @Override
    public Token CreateToken(User user, Credentials credentials) {

        String randomValue= UUID.randomUUID().toString();
        Token token=new Token();
        token.setToken(randomValue);
        token.setUser(user);
        return  tokenRepository.save(token);
    }

    @Override
    public User verifyToken(String authorizationHeader) {
        if(authorizationHeader==null) return null;
        String token = authorizationHeader.split(" ")[1];
        var tokenInDb=tokenRepository.findById(token);
        if(tokenInDb.isEmpty())return null;
        return tokenInDb.get().getUser();

    }

    @Override
    public void logout(String authHeader) {
        if(authHeader==null) return;
        String token = authHeader.split(" ")[1];
        var tokenInDb=tokenRepository.findById(token);
        if(tokenInDb.isPresent())tokenRepository.deleteById(tokenInDb.get().getToken());

    }
}
