package com.tobeto.pair3.security;

import com.tobeto.pair3.entities.User;
import com.tobeto.pair3.services.abstracts.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService  implements UserDetailsService {

    private UserService userService;

    public AppUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User inDb = userService.findByEmail(email);
        if (inDb == null) throw new UsernameNotFoundException(email + "not found");

        return new CurrentUser(inDb);
    }
}