package com.data.pojo;

import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

public class TestDriveRequest {

    @NotEmpty(message = "Car ID is required")
    private Long carId;

    private String drivingLicenseNumber;
    private AppointmentRequest appointmentRequest; // New field for Appointment Request

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

    public AppointmentRequest getAppointmentRequest() {
        return appointmentRequest;
    }

    public void setAppointmentRequest(AppointmentRequest appointmentRequest) {
        this.appointmentRequest = appointmentRequest;
    }
}
