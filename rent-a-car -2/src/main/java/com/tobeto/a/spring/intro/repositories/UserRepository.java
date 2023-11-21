package com.tobeto.a.spring.intro.repositories;


import com.tobeto.a.spring.intro.entities.User;
import com.tobeto.a.spring.intro.entities.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}