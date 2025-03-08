package com.data.dto;

import java.sql.Timestamp;

public class PaymentDTO {
    private Long id;
    private Long vendorId;
    private Double amount;
    private Timestamp paymentDate;
    private String status;

    public PaymentDTO(Long id, Long vendorId, Double amount, Timestamp paymentDate, String status) {
        this.id = id;
        this.vendorId = vendorId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Timestamp getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Timestamp paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
