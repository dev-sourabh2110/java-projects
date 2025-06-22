package com.data.dto.car;

/**
 * Full representation of car details for complete views
 * Extends BaseCarDTO to inherit common fields
 */
public class CarDetailsDTO extends BaseCarDTO {
    private String year;
    private String condition;
    private String stockNumber;
    private String vinNumber;
    private Double regularPrice;
    private Double salePrice;
    private Double requestPrice;
    private String description;
    private boolean priceLabel;
    private String status;
    
    // Default constructor for frameworks
    public CarDetailsDTO() {
    }
    
    // The builder allows flexible construction of the DTO
    public static Builder builder() {
        return new Builder();
    }
    
    // Getters and Setters
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(String stockNumber) {
        this.stockNumber = stockNumber;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public Double getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(Double regularPrice) {
        this.regularPrice = regularPrice;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Double getRequestPrice() {
        return requestPrice;
    }

    public void setRequestPrice(Double requestPrice) {
        this.requestPrice = requestPrice;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPriceLabel() {
        return priceLabel;
    }

    public void setPriceLabel(boolean priceLabel) {
        this.priceLabel = priceLabel;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * Builder pattern implementation for CarDetailsDTO
     */
    public static class Builder {
        private Long id;
        private String title;
        private String make;
        private String model;
        private String type;
        private String year;
        private String condition;
        private String stockNumber;
        private String vinNumber;
        private Double regularPrice;
        private Double salePrice;
        private Double requestPrice;
        private String description;
        private boolean priceLabel;
        private String status;
        
        private Builder() {
        }
        
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder title(String title) {
            this.title = title;
            return this;
        }
        
        public Builder make(String make) {
            this.make = make;
            return this;
        }
        
        public Builder model(String model) {
            this.model = model;
            return this;
        }
        
        public Builder type(String type) {
            this.type = type;
            return this;
        }
        
        public Builder year(String year) {
            this.year = year;
            return this;
        }
        
        public Builder condition(String condition) {
            this.condition = condition;
            return this;
        }
        
        public Builder stockNumber(String stockNumber) {
            this.stockNumber = stockNumber;
            return this;
        }
        
        public Builder vinNumber(String vinNumber) {
            this.vinNumber = vinNumber;
            return this;
        }
        
        public Builder regularPrice(Double regularPrice) {
            this.regularPrice = regularPrice;
            return this;
        }
        
        public Builder salePrice(Double salePrice) {
            this.salePrice = salePrice;
            return this;
        }
        
        public Builder requestPrice(Double requestPrice) {
            this.requestPrice = requestPrice;
            return this;
        }
        
        public Builder description(String description) {
            this.description = description;
            return this;
        }
        
        public Builder priceLabel(boolean priceLabel) {
            this.priceLabel = priceLabel;
            return this;
        }
        
        public Builder status(String status) {
            this.status = status;
            return this;
        }
        
        public CarDetailsDTO build() {
            CarDetailsDTO dto = new CarDetailsDTO();
            dto.setId(id);
            dto.setTitle(title);
            dto.setMake(make);
            dto.setModel(model);
            dto.setType(type);
            dto.setYear(year);
            dto.setCondition(condition);
            dto.setStockNumber(stockNumber);
            dto.setVinNumber(vinNumber);
            dto.setRegularPrice(regularPrice);
            dto.setSalePrice(salePrice);
            dto.setRequestPrice(requestPrice);
            dto.setDescription(description);
            dto.setPriceLabel(priceLabel);
            dto.setStatus(status);
            return dto;
        }
    }
}