package com.tobeto.a.spring.intro.repositories;


import com.tobeto.a.spring.intro.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}