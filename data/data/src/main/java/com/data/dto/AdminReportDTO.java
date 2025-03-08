package com.data.dto;

public class AdminReportDTO {
    private double totalRevenue;
    private long totalOffers;
    private long totalTestDrives;
    private String mostSoldCar;
    private String mostActiveVendor;

    public AdminReportDTO(double totalRevenue, long totalOffers, long totalTestDrives, String mostSoldCar, String mostActiveVendor) {
        this.totalRevenue = totalRevenue;
        this.totalOffers = totalOffers;
        this.totalTestDrives = totalTestDrives;
        this.mostSoldCar = mostSoldCar;
        this.mostActiveVendor = mostActiveVendor;
    }

    // Getters & Setters

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public long getTotalOffers() {
        return totalOffers;
    }

    public void setTotalOffers(long totalOffers) {
        this.totalOffers = totalOffers;
    }

    public long getTotalTestDrives() {
        return totalTestDrives;
    }

    public void setTotalTestDrives(long totalTestDrives) {
        this.totalTestDrives = totalTestDrives;
    }

    public String getMostSoldCar() {
        return mostSoldCar;
    }

    public void setMostSoldCar(String mostSoldCar) {
        this.mostSoldCar = mostSoldCar;
    }

    public String getMostActiveVendor() {
        return mostActiveVendor;
    }

    public void setMostActiveVendor(String mostActiveVendor) {
        this.mostActiveVendor = mostActiveVendor;
    }
}
