package com.tobeto.a.spring.intro.entities;


import jakarta.persistence.*;

import java.util.List;

@Table(name="users")
@Entity
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserDetails userDetails;

    @OneToMany(mappedBy = "user")
    private List<Rental> rentals;
}
