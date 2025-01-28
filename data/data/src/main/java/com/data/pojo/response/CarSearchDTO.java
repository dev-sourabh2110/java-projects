package com.data.pojo.response;

import java.util.List;

public class CarSearchDTO {
    private Long id;
    private String title;
    private String make;
    private String model;
    private String type;
    private Double regularPrice;
    private List<String> photoUrls;

    public CarSearchDTO(Long id, String title, String make, String model, String type, Double regularPrice, List<String> photoUrls) {
        this.id = id;
        this.title = title;
        this.make = make;
        this.model = model;
        this.type = type;
        this.regularPrice = regularPrice;
        this.photoUrls = photoUrls;
    }

    // Getters and Setters

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

    public Double getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(Double regularPrice) {
        this.regularPrice = regularPrice;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }
}
