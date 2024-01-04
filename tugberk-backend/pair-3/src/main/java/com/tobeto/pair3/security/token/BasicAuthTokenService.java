package com.tobeto.pair3.security.token;


import com.tobeto.pair3.entities.User;
import com.tobeto.pair3.security.Credentials;
import com.tobeto.pair3.services.abstracts.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class BasicAuthTokenService implements TokenService{

    private UserService userService;

    PasswordEncoder passwordEncoder;

    public BasicAuthTokenService(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Token CreateToken(User user, Credentials credentials) {
        String emailColonPassword=credentials.email()+":"+credentials.password();
        String token=Base64.getEncoder().encodeToString(emailColonPassword.getBytes());

        return new Token("Basic",token);
    }

    @Override
    public User verifyToken(String authorizationHeader) {

        if(authorizationHeader==null) return null;

       var base64Encoded= authorizationHeader.split("Basic ")[1];

        var decodedValue=new String(Base64.getDecoder().decode(base64Encoded));

        var credentials=decodedValue.split(":");
        var email=credentials[0];
        var password=credentials[1];

        User inDb=userService.findByEmail(email);

        if(inDb == null) return null;

        if(!passwordEncoder.matches(password,inDb.getPassword()))return  null;
        return inDb;




    }

    @Override
    public void logout(String authHeader) {

    }
}
