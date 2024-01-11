package com.tobeto.pair3.repositories;

import com.tobeto.pair3.entities.Rental;
import com.tobeto.pair3.entities.RentalStatus;
import com.tobeto.pair3.services.dtos.responses.GetRentalResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental,Integer> {
    List<Rental> findByUserId(int id);
    @Transactional
    @Modifying
    @Query("UPDATE Rental r SET r.status = :newStatus WHERE r.returnDate < CURRENT_DATE")
    void updateStatusToOverdue(@Param("newStatus") RentalStatus newStatus);


    @Query("SELECT r FROM Rental r WHERE r.status = :newStatus")
    List<Rental> findByStatus(@Param("newStatus") RentalStatus newStatus);

}
