package com.data.dto;

import java.sql.Timestamp;
import java.util.Date;

public record VendorProfileDTO(
    Long vendorId,
    Long userId,
    String firstName,
    String lastName,
    String email,
    String phoneNumber,
    String address,
    Date dob,
    String tradeLicenseNumber,
    boolean enabled,
    Timestamp createTime,
    Timestamp updateTime
) {}
