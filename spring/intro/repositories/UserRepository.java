package com.tobeto.a.spring.intro.repositories;


import com.tobeto.a.spring.intro.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("SELECT u FROM User u " +
            "WHERE (LOWER(u.name) = COALESCE(LOWER(:name), LOWER(u.name)) OR :name IS NULL) " +
            "AND (LOWER(CAST(u.email AS text)) = COALESCE(LOWER(:email), LOWER(CAST(u.email AS text))) OR :email IS NULL) " +
            "AND (LOWER(u.phone) = COALESCE(LOWER(:phone), LOWER(u.phone)) OR :phone IS NULL) " +
            "AND (LOWER(u.address) = COALESCE(LOWER(:address), LOWER(u.address)) OR :address IS NULL)")
    List<User> findByDynamicFilters(
            @Param("name") String name,
            @Param("email") String email,
            @Param("phone") String phone,
            @Param("address") String address
    );

    @Query("select u from User u where u.name LIKE CONCAT('%', :input, '%')")
    List<User> findLikeNameByInput(String input);



}

/* @Query("SELECT c FROM Car c WHERE (:year is null OR c.year = :year) " +
            "AND (:fuelType is null OR LOWER(c.fuelType) = LOWER(:fuelType)) " +
            "AND (:color is null OR LOWER(c.color) = LOWER(:color))")
    List<Car> findByDynamicFilters(
            @Param("year") Integer year,
            @Param("fuelType") String fuelType,
            @Param("color") String color
    );
*/