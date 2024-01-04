package com.tobeto.pair3.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "rentals")
@Data
public class Rental {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "start_kilometer")
    private Integer startKilometer;

    @Column(name = "end_kilometer",nullable = true)
    private Integer endKilometer;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "pickup_location")
    private String  pickupLocation;

    @Column(name = "drop_off_location")
    private String  dropOffLocation;



    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "rental")
    private List<Invoice> invoices;


}
