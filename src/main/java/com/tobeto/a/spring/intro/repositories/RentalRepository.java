package com.tobeto.a.spring.intro.repositories;


import com.tobeto.a.spring.intro.entities.Rental;
import com.tobeto.a.spring.intro.services.models.requests.CreatePaymentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;


public interface RentalRepository extends JpaRepository<Rental,Integer> {


}