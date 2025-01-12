package com.data.pojo.response;

import java.util.Date;

public class AppointmentDTO {

    private String carModel;
    private Date appointmentDate;

    public AppointmentDTO(String carModel, Date appointmentDate) {
        this.carModel = carModel;
        this.appointmentDate = appointmentDate;
    }

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

