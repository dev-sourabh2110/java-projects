package com.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "car_features")
public class CarFeaturesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "car_id", nullable = false)
    private CarEntity car;

    private boolean ACFront;
    private boolean ACRear;
    private boolean backupCamera;
    private boolean cruiseControl;
    private boolean navigation;
    private boolean powerLocks;
    private boolean sunroof;
    private boolean heatedSeats;
    private boolean bluetooth;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }

    public boolean isACFront() {
        return ACFront;
    }

    public void setACFront(boolean ACFront) {
        this.ACFront = ACFront;
    }

    public boolean isACRear() {
        return ACRear;
    }

    public void setACRear(boolean ACRear) {
        this.ACRear = ACRear;
    }

    public boolean isBackupCamera() {
        return backupCamera;
    }

    public void setBackupCamera(boolean backupCamera) {
        this.backupCamera = backupCamera;
    }

    public boolean isCruiseControl() {
        return cruiseControl;
    }

    public void setCruiseControl(boolean cruiseControl) {
        this.cruiseControl = cruiseControl;
    }

    public boolean isNavigation() {
        return navigation;
    }

    public void setNavigation(boolean navigation) {
        this.navigation = navigation;
    }

    public boolean isPowerLocks() {
        return powerLocks;
    }

    public void setPowerLocks(boolean powerLocks) {
        this.powerLocks = powerLocks;
    }

    public boolean isSunroof() {
        return sunroof;
    }

    public void setSunroof(boolean sunroof) {
        this.sunroof = sunroof;
    }

    public boolean isHeatedSeats() {
        return heatedSeats;
    }

    public void setHeatedSeats(boolean heatedSeats) {
        this.heatedSeats = heatedSeats;
    }

    public boolean isBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(boolean bluetooth) {
        this.bluetooth = bluetooth;
    }
}

