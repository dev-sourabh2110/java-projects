package com.data.pojo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public class MakeOfferRequest {

    @NotEmpty(message = "Car ID is required")
    private Long carId;

    @NotEmpty(message = "Driving License Number is required")
    private String drivingLicenseNumber;

    @Positive(message = "Offered Price must be a positive number")
    private Double offeredPrice;

    @Positive(message = "Final Offered Price must be a positive number")
    private Double finalOfferedPrice;

    // Getters and Setters

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    public void setDrivingLicenseNumber(String drivingLicenseNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public Double getOfferedPrice() {
        return offeredPrice;
    }

    public void setOfferedPrice(Double offeredPrice) {
        this.offeredPrice = offeredPrice;
    }

    public Double getFinalOfferedPrice() {
        return finalOfferedPrice;
    }

    public void setFinalOfferedPrice(Double finalOfferedPrice) {
        this.finalOfferedPrice = finalOfferedPrice;
    }
}
