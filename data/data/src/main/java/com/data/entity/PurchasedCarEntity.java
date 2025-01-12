package com.data.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "purchased_cars")
public class PurchasedCarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_email")
    private UserEntity user;

    private String carModel;
    private Date purchaseDate;
    private Double price;

    // Getters and Setters
}
