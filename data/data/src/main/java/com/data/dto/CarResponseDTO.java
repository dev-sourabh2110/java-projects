package com.data.dto;

import java.sql.Timestamp;

public class CarResponseDTO {
    private Long id;
    private String title;
    private String make;
    private String model;
    private String type;
    private String status; // PENDING, APPROVED, REJECTED, SOLD
    private Double price;
    private Timestamp createTime;
    private Timestamp updateTime;

    public CarResponseDTO(Long id, String title, String make, String model, String type, String status,
                          Double price, Timestamp createTime, Timestamp updateTime) {
        this.id = id;
        this.title = title;
        this.make = make;
        this.model = model;
        this.type = type;
        this.status = status;
        this.price = price;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
