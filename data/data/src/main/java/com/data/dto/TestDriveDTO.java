package com.data.dto;

import java.sql.Timestamp;

public class TestDriveDTO {
    private Long id;
    private UserDTO user;
    private String drivingLicenseNumber;
    private Timestamp createTime;

    public TestDriveDTO(Long id, UserDTO user, String drivingLicenseNumber, Timestamp createTime) {
        this.id = id;
        this.user = user;
        this.drivingLicenseNumber = drivingLicenseNumber;
        this.createTime = createTime;
    }

    // Getters & Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    public void setDrivingLicenseNumber(String drivingLicenseNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
