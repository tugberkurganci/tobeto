package com.tobeto.pair3.security;

import com.tobeto.pair3.entities.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class CurrentUser implements UserDetails {

    private int id;

    private String name;
    private String password;

    private boolean enabled;

    private List<String> authorities;

    public CurrentUser(User user)
    {

        this.id= user.getId();
        this.name=user.getName();
        this.password=user.getPassword();
        this.enabled=user.getActive();
        this.authorities=user.getAuthorities();

    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(this.authorities.size()>0){
        for (String role : this.authorities) {
            authorities.add(new SimpleGrantedAuthority(role));
        }}
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}