package com.data.pojo;

import jakarta.validation.constraints.NotEmpty;

public class ForgetPasswordRequest {

    @NotEmpty(message = "Email or Phone is required")
    private String emailOrPhone;

    // Getters and Setters
    public String getEmailOrPhone() {
        return emailOrPhone;
    }

    public void setEmailOrPhone(String emailOrPhone) {
        this.emailOrPhone = emailOrPhone;
    }
}
