package com.tobeto.a.spring.intro.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

// ORM => Object Relation Mapping
@Table(name = "cars")
@Entity
public class Car
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="model_year")
    private int modelYear;

    @Column(name="model_name")
    private String modelName;

    @Column(name="daily_price")
    private double dailyPrice;

    @Column(name="color")
    private String color;

    @Column(name="status")
    private String status;


    @ManyToOne()
    @JoinColumn(name="brand_id")
    private Brand brand;

    @ManyToMany(mappedBy = "cars")
    private List<Rental> rentals;

    @ManyToOne
    @JoinColumn(name = "car_supplier_id")
    private CarSupplier carSupplier;
}
