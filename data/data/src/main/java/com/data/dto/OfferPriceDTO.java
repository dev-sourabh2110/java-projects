package com.data.dto;

import java.sql.Timestamp;

public class OfferPriceDTO {
    private Long id;
    private UserDTO user;
    private Long carId;
    private Double offerPrice;
    private String status; // PENDING, APPROVED, REJECTED
    private Timestamp createTime;

    public OfferPriceDTO(Long id, UserDTO user, Long carId, Double offerPrice, String status, Timestamp createTime) {
        this.id = id;
        this.user = user;
        this.carId = carId;
        this.offerPrice = offerPrice;
        this.status = status;
        this.createTime = createTime;
    }

    // Getters & Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Double getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(Double offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
