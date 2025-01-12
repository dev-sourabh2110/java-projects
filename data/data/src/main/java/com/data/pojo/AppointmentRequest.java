package com.data.pojo;

import java.util.Date;

public class AppointmentRequest {

    private String carModel;
    private Date appointmentDate;

    // Getters and Setters

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
}
