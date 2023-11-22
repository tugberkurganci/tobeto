package com.tobeto.a.spring.intro.controllers;

import com.tobeto.a.spring.intro.entities.Payment;
import com.tobeto.a.spring.intro.repositories.PaymentRepository;
import com.tobeto.a.spring.intro.services.abstracts.PaymentService;
import com.tobeto.a.spring.intro.services.models.requests.CreatePaymentRequest;
import com.tobeto.a.spring.intro.services.models.requests.UpdatePaymentRequest;
import com.tobeto.a.spring.intro.services.models.responses.PaymentResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;


    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public List<PaymentResponse> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public PaymentResponse getPaymentById(@PathVariable int id) {
        return paymentService.getPaymentById(id);
    }

    @PostMapping
    public void createPayment(@RequestBody CreatePaymentRequest paymentRequest) {
        paymentService.createPayment(paymentRequest);
    }

    @PutMapping
    public void updatePayment(@RequestBody UpdatePaymentRequest updatedPaymentRequest) {
        paymentService.updatePayment(updatedPaymentRequest);
    }

    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable int id) {
        paymentService.deletePayment(id);
    }
}
