
package com.data.dto.car;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO for search results, containing minimal car information plus media
 */
public class CarSearchResultDTO extends BaseCarDTO {
    private Double regularPrice;
    private List<String> photoUrls = new ArrayList<>();
    
    // Default constructor
    public CarSearchResultDTO() {
    }
    
    // Builder
    public static Builder builder() {
        return new Builder();
    }
    
    // Getters and setters
    public Double getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(Double regularPrice) {
        this.regularPrice = regularPrice;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }
    
    /**
     * Builder for search result DTO
     */
    public static class Builder {
        private Long id;
        private String title;
        private String make;
        private String model;
        private String type;
        private Double regularPrice;
        private List<String> photoUrls = new ArrayList<>();
        
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
        
        public Builder regularPrice(Double regularPrice) {
            this.regularPrice = regularPrice;
            return this;
        }
        
        public Builder photoUrls(List<String> photoUrls) {
            this.photoUrls = photoUrls;
            return this;
        }
        
        public Builder addPhotoUrl(String photoUrl) {
            this.photoUrls.add(photoUrl);
            return this;
        }
        
        public CarSearchResultDTO build() {
            CarSearchResultDTO dto = new CarSearchResultDTO();
            dto.setId(id);
            dto.setTitle(title);
            dto.setMake(make);
            dto.setModel(model);
            dto.setType(type);
            dto.setRegularPrice(regularPrice);
            dto.setPhotoUrls(photoUrls);
            return dto;
        }
    }
}