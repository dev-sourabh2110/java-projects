package com.data.dto;

import java.util.Set;

public record LoginResponse(Long userId, Long vendorId, Long adminId, String email, Set<String> roles) {
}
