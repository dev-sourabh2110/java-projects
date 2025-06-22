package com.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties({"createTime", "updateTime"})
@JsonIncludeProperties
public record CarDTO(
        Long id,
        String title,
        String make,
        String model,
        String type,
        String status,
        BigDecimal price,
        String year,
        String condition,
        String stockNumber,
        String vinNumber,
        Double regularPrice,
        Double salePrice,
        Double requestPrice,
        String description,
        boolean priceLabel
) {
}