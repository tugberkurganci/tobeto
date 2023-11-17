package com.tobeto.a.spring.intro.entities;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Table(name="rentals")
@Entity
public class Rental {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "rental_date")
    private Date rentalDate;

    @Column(name = "return_date")
    private Date returnDate;

    @OneToOne(mappedBy = "rental")
    private Payment payment;

    @ManyToOne()
    @JoinColumn(name="user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "rental_car",
            joinColumns = @JoinColumn(name = "rental_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id")
    )
    private List<Car> cars ;

}
