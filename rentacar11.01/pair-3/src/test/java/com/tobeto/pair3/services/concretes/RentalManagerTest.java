package com.tobeto.pair3.services.concretes;

import com.tobeto.pair3.entities.Rental;
import com.tobeto.pair3.entities.RentalStatus;
import com.tobeto.pair3.repositories.RentalRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RentalManagerTest {

    @Autowired
    private RentalRepository rentalRepository;

    @Test
    public void testUpdateStatusToOverdue() {
        Rental rental = new Rental();
        rental.setReturnDate(LocalDate.now().minusDays(1));
        rental.setStatus(RentalStatus.ACTIVE);
        Rental rental1=rentalRepository.save(rental);

        rentalRepository.updateStatusToOverdue(RentalStatus.OVERDUE);


        Rental updatedRental = rentalRepository.findById(rental1.getId()).orElse(null);

        // Durumu "OVERDUE" olmalı
        assertEquals(RentalStatus.OVERDUE, updatedRental.getStatus());
    }
}
