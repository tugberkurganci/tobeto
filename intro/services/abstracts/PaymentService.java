package com.tobeto.a.spring.intro.services.abstracts;

import com.tobeto.a.spring.intro.entities.Payment;
import com.tobeto.a.spring.intro.services.models.requests.CreatePaymentRequest;
import com.tobeto.a.spring.intro.services.models.requests.UpdatePaymentRequest;
import com.tobeto.a.spring.intro.services.models.responses.PaymentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {
    List<PaymentResponse> getAllPayments();
    PaymentResponse getPaymentById(int id);

    void updatePayment(UpdatePaymentRequest updatedPaymentRequest);

    int createPayment(CreatePaymentRequest paymentRequest);

    void deletePayment(int id);

    Payment getOriginalPaymentById(int paymentId);
}