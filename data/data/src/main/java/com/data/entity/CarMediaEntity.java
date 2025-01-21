package com.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "car_media")
public class CarMediaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "car_id", nullable = false)
    private CarEntity car;

    @Lob
    private byte[] photo1;

    @Lob
    private byte[] photo2;
    @Lob
    private byte[] photo3;
    @Lob
    private byte[] photo4;
    @Lob
    private byte[] photo5;
    @Lob
    private byte[] photo6;

    private String videoUrl;

    @Lob
    private byte[] vinReport;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }

    public byte[] getPhoto1() {
        return photo1;
    }

    public void setPhoto1(byte[] photo1) {
        this.photo1 = photo1;
    }

    public byte[] getPhoto2() {
        return photo2;
    }

    public void setPhoto2(byte[] photo2) {
        this.photo2 = photo2;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public byte[] getVinReport() {
        return vinReport;
    }

    public void setVinReport(byte[] vinReport) {
        this.vinReport = vinReport;
    }

    public byte[] getPhoto3() {
        return photo3;
    }

    public void setPhoto3(byte[] photo3) {
        this.photo3 = photo3;
    }

    public byte[] getPhoto4() {
        return photo4;
    }

    public void setPhoto4(byte[] photo4) {
        this.photo4 = photo4;
    }

    public byte[] getPhoto5() {
        return photo5;
    }

    public void setPhoto5(byte[] photo5) {
        this.photo5 = photo5;
    }

    public byte[] getPhoto6() {
        return photo6;
    }

    public void setPhoto6(byte[] photo6) {
        this.photo6 = photo6;
    }
}

