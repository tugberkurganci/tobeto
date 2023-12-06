package com.tobeto.a.spring.intro.services.concretes;

import com.tobeto.a.spring.intro.entities.Payment;
import com.tobeto.a.spring.intro.repositories.PaymentRepository;
import com.tobeto.a.spring.intro.services.abstracts.PaymentService;
import com.tobeto.a.spring.intro.services.abstracts.RentalService;
import com.tobeto.a.spring.intro.services.models.requests.CreatePaymentRequest;
import com.tobeto.a.spring.intro.services.models.requests.UpdatePaymentRequest;
import com.tobeto.a.spring.intro.services.models.responses.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentManager implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final RentalService rentalService;


    @Override
    public List<PaymentResponse> getAllPayments() {
        List<Payment> paymentList = paymentRepository.findAll();
        return paymentList.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentResponse getPaymentById(int id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
        return convertToResponse(payment);
    }

    @Override
    public int createPayment(CreatePaymentRequest paymentRequest) {
        if (paymentRequest.getPaymentAmount().compareTo(new BigDecimal(1000000)) > 0) {
            throw new RuntimeException("Amount is too high; it is not possible to pay with cash");
        }

        Payment payment = new Payment();

        payment.setPaymentAmount(paymentRequest.getPaymentAmount());
        payment.setPaymentDate(paymentRequest.getPaymentDate());
        payment.setPaymentOption(paymentRequest.getPaymentOption());


        return  paymentRepository.save(payment).getId();
    }

    @Override
    public void updatePayment(UpdatePaymentRequest updatedPaymentRequest) {
        Optional<Payment> existingPayment = paymentRepository.findById(updatedPaymentRequest.getId());

        if (existingPayment.isPresent()) {
            Payment payment = existingPayment.get();
            payment.setPaymentAmount(updatedPaymentRequest.getPaymentAmount());
            payment.setPaymentDate(updatedPaymentRequest.getPaymentDate());
            payment.setPaymentOption(updatedPaymentRequest.getPaymentOption());
            payment.setRental(rentalService.getOriginalRentalById(updatedPaymentRequest.getRentalId()));

            paymentRepository.save(payment);
        } else {
            throw new RuntimeException("There is no payment");
        }
    }

    @Override
    public void deletePayment(int id) {
        if (paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Payment not found with id: " + id);
        }
    }

    @Override
    public Payment getOriginalPaymentById(int paymentId) {
        return paymentRepository.findById(paymentId).orElseThrow();
    }

    @Override
    public List<PaymentResponse> sortedPaymentsByDate() {
        return paymentRepository.sortedPaymentsByDate();
    }

    @Override
    public List<PaymentResponse> getPaymentsByUserId(int id) {
        return paymentRepository.getPaymentsByUserId(id);
    }

    private PaymentResponse convertToResponse(Payment payment) {
        PaymentResponse paymentResponse = new PaymentResponse();

        paymentResponse.setPaymentAmount(payment.getPaymentAmount());
        paymentResponse.setPaymentDate(payment.getPaymentDate());
        paymentResponse.setPaymentOption(payment.getPaymentOption());
        paymentResponse.setRentalId(payment.getRental().getId());

        return paymentResponse;
    }


}
