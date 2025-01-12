package com.data.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String carModel;
    private Date appointmentDate;

    @ManyToOne
    @JoinColumn(name = "test_drive_id", referencedColumnName = "id", nullable = false)
    private TestDrive testDrive;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public TestDrive getTestDrive() {
        return testDrive;
    }

    public void setTestDrive(TestDrive testDrive) {
        this.testDrive = testDrive;
    }
}
