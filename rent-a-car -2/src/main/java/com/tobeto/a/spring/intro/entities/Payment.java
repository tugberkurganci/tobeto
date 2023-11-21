package com.tobeto.a.spring.intro.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "payment_amount")
    private BigDecimal paymentAmount;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "payment_option")
    private String paymentOption;

    @OneToOne
    @JoinColumn(name = "rental_id", unique = true)
    private Rental rental;


}