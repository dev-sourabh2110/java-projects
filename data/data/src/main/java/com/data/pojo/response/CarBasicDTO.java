package com.data.pojo.response;

import java.sql.Timestamp;
import java.util.List;

public class CarBasicDTO {
    private Long id;
    private String title;
    private String make;
    private String model;
    private String type;
    private String year;
    private String condition;
    private String stockNumber;
    private String vinNumber;
    private Double regularPrice;
    private Double salePrice;
    private Double requestPrice;
    private String description;
    private boolean priceLabel;
    private Timestamp createTime;
    private Timestamp updateTime;
    private CarMediaDTO media;

    public CarBasicDTO() {
    }

    public CarBasicDTO(Long id, String title, String make, String model, String type, String year,
                       String condition, String stockNumber, String vinNumber, Double regularPrice,
                       Double salePrice, Double requestPrice, String description, boolean priceLabel,
                       Timestamp createTime, Timestamp updateTime, CarMediaDTO media) {
        this.id = id;
        this.title = title;
        this.make = make;
        this.model = model;
        this.type = type;
        this.year = year;
        this.condition = condition;
        this.stockNumber = stockNumber;
        this.vinNumber = vinNumber;
        this.regularPrice = regularPrice;
        this.salePrice = salePrice;
        this.requestPrice = requestPrice;
        this.description = description;
        this.priceLabel = priceLabel;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.media = media;
    }

    // Getters and Setters

    // ... (omitted for brevity)
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // (include getters and setters for all fields)
    
    public CarMediaDTO getMedia() {
        return media;
    }

    public void setMedia(CarMediaDTO media) {
        this.media = media;
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
}
