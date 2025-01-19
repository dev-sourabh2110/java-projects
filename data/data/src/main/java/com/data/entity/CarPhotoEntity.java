package com.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "car_photos")
public class CarPhotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(nullable = false)
    private byte[] photo; // BLOB to store photo data

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private CarEntity car; // Association with CarEntity

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }
}

