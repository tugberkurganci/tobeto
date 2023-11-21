package com.tobeto.a.spring.intro.repositories;

import com.tobeto.a.spring.intro.entities.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails,Integer> {
}