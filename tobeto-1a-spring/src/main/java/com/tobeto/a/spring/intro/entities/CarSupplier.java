package com.tobeto.a.spring.intro.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "car_suppliers")
public class CarSupplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @OneToMany(mappedBy = "carSupplier")
    private List<Car> cars ;

}