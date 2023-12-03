package com.tobeto.a.spring.intro.repositories;

import com.tobeto.a.spring.intro.entities.CarSupplier;
import com.tobeto.a.spring.intro.entities.Payment;
import com.tobeto.a.spring.intro.services.models.responses.PaymentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    @Query("select  new com.tobeto.a.spring.intro.services.models.responses.PaymentResponse(p.paymentAmount,p.paymentDate,p.paymentOption,p.rental.id) " +
            "from Payment p order by p.paymentDate")
    List<PaymentResponse> sortedPaymentsByDate();

    @Query(
            "select  new com.tobeto.a.spring.intro.services.models.responses.PaymentResponse(p.paymentAmount,p.paymentDate,p.paymentOption,p.rental.id) " +
                    "from Payment p where p.rental.user.id=:id order by p.paymentDate desc"
    )
    List<PaymentResponse> getPaymentsByUserId(int id);
}
