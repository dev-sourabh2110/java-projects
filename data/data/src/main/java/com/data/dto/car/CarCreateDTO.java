
package com.data.dto.car;

import java.io.Serializable;

/**
 * Request DTO for creating a new car
 * This is deliberately separate from the other DTOs since it's for input
 */
public class CarCreateDTO implements Serializable {
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
    
    // Default constructor
    public CarCreateDTO() {
    }
    
    // All getters and setters
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
}