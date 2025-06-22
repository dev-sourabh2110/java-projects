package com.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "cars")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;
    private String title;
    private String make;
    private String model;
    private String type;
    private String year;
    // Use a custom column name for "condition" as it is a reserved keyword
    @Column(name = "car_condition")
    private String condition;
    private String stockNumber;
    private String vinNumber;
    private Double regularPrice;
    private Double salePrice;
    private Double requestPrice;
    @Column(columnDefinition = "TEXT")
    private String description;
    // Assuming priceLabel is used as a flag; if you prefer a different type, adjust accordingly
    private boolean priceLabel;

    // Timestamp fields for tracking creation and update times
    @Column(name = "create_time", nullable = false, updatable = false)
    private Timestamp createTime;
    @Column(name = "update_time", nullable = false)
    private Timestamp updateTime;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "car", fetch = FetchType.LAZY)
    @JsonManagedReference // Prevent infinite recursion
    private CarSpecificationsEntity specifications;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "car", fetch = FetchType.LAZY)
    @JsonManagedReference // Prevent infinite recursion
    private CarFeaturesEntity features;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "car", fetch = FetchType.LAZY)
    @JsonManagedReference // Prevent infinite recursion
    private CarMediaEntity media;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "car", fetch = FetchType.LAZY)
    @JsonManagedReference // Prevent infinite recursion
    private CarAddressEntity address;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id", nullable = false)
    @JsonManagedReference // Prevent infinite recursion
    private VendorEntity vendor;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPriceLabel() {
        return priceLabel;
    }

    public void setPriceLabel(boolean priceLabel) {
        this.priceLabel = priceLabel;
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

    // Auto-update timestamps
    @PrePersist
    protected void onCreate() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        this.createTime = now;
        this.updateTime = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateTime = new Timestamp(System.currentTimeMillis());
    }
}
