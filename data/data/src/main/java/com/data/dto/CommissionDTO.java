package com.data.dto;

public class CommissionDTO {
    private String category;
    private Double value;
    private boolean isPercentage;

    public CommissionDTO(String category, Double value, boolean isPercentage) {
        this.category = category;
        this.value = value;
        this.isPercentage = isPercentage;
    }

    // Getters & Setters

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public boolean isPercentage() {
        return isPercentage;
    }

    public void setPercentage(boolean percentage) {
        isPercentage = percentage;
    }
}
