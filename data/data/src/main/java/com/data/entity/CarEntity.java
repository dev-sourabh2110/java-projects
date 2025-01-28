package com.data.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "cars")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String make;
    private String model;
    private String type;
    private String year;
    @Column(name = "car_condition")
    private String condition;
    private String stockNumber;
    private String vinNumber;
    private Double regularPrice;
    private Double salePrice;
    private Double requestPrice;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "car")
    @JsonManagedReference // Prevent infinite recursion
    private CarSpecificationsEntity specifications;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "car")
    @JsonManagedReference // Prevent infinite recursion
    private CarFeaturesEntity features;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "car")
    @JsonManagedReference // Prevent infinite recursion
    private CarMediaEntity media;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "car")
    @JsonManagedReference // Prevent infinite recursion
    private CarAddressEntity address;

    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    private VendorEntity vendor;

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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(String stockNumber) {
        this.stockNumber = stockNumber;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public Double getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(Double regularPrice) {
        this.regularPrice = regularPrice;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Double getRequestPrice() {
        return requestPrice;
    }

    public void setRequestPrice(Double requestPrice) {
        this.requestPrice = requestPrice;
    }

    public CarSpecificationsEntity getSpecifications() {
        return specifications;
    }

    public void setSpecifications(CarSpecificationsEntity specifications) {
        this.specifications = specifications;
    }

    public CarFeaturesEntity getFeatures() {
        return features;
    }

    public void setFeatures(CarFeaturesEntity features) {
        this.features = features;
    }

    public CarMediaEntity getMedia() {
        return media;
    }

    public void setMedia(CarMediaEntity media) {
        this.media = media;
    }

    public CarAddressEntity getAddress() {
        return address;
    }

    public void setAddress(CarAddressEntity address) {
        this.address = address;
    }

    public VendorEntity getVendor() {
        return vendor;
    }

    public void setVendor(VendorEntity vendor) {
        this.vendor = vendor;
    }
}
