package com.tobeto.a.spring.intro.controllers;

import com.tobeto.a.spring.intro.entities.Payment;
import com.tobeto.a.spring.intro.repositories.PaymentRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentRepository paymentRepository;

    public PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable int id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
    }

    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentRepository.save(payment);
    }

    @PutMapping
    public Payment updatePayment( @RequestBody Payment updatedPayment) {
        Optional<Payment> existingPayment = paymentRepository.findById(updatedPayment.getId());

        if (existingPayment.isPresent()) {
            Payment payment = existingPayment.get();
            payment.setPaymentAmount(updatedPayment.getPaymentAmount());
            payment.setPaymentDate(updatedPayment.getPaymentDate());
            payment.setPaymentOption(updatedPayment.getPaymentOption());
            // rental ilişkisini güncelleme (eğer gerekirse)
            payment.setRental(updatedPayment.getRental());

            return paymentRepository.save(payment);
        } else {
            throw new RuntimeException("Payment not found with id: " + updatedPayment.getId());
        }
    }

    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable int id) {
        if (paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Payment not found with id: " + id);
        }
    }
}
