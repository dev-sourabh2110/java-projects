package com.data.dto.car;

import java.sql.Timestamp;

/**
 * DTO for car status views in admin listings
 */
public class CarStatusDTO extends BaseCarDTO {
    private String status; // PENDING, APPROVED, REJECTED, SOLD
    private Double price;
    private Timestamp createTime;
    private Timestamp updateTime;
    
    // Default constructor
    public CarStatusDTO() {
    }
    
    // Builder
    public static Builder builder() {
        return new Builder();
    }
    
    // Getters and setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
    
    /**
     * Builder for status DTO
     */
    public static class Builder {
        private Long id;
        private String title;
        private String make;
        private String model;
        private String type;
        private String status;
        private Double price;
        private Timestamp createTime;
        private Timestamp updateTime;
        
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
        
        public Builder status(String status) {
            this.status = status;
            return this;
        }
        
        public Builder price(Double price) {
            this.price = price;
            return this;
        }
        
        public Builder createTime(Timestamp createTime) {
            this.createTime = createTime;
            return this;
        }
        
        public Builder updateTime(Timestamp updateTime) {
            this.updateTime = updateTime;
            return this;
        }
        
        public CarStatusDTO build() {
            CarStatusDTO dto = new CarStatusDTO();
            dto.setId(id);
            dto.setTitle(title);
            dto.setMake(make);
            dto.setModel(model);
            dto.setType(type);
            dto.setStatus(status);
            dto.setPrice(price);
            dto.setCreateTime(createTime);
            dto.setUpdateTime(updateTime);
            return dto;
        }
    }
}