package com.data.mapper;

import com.data.dto.car.*;
import com.data.entity.*;
import com.data.enums.CarCondition;
import com.data.enums.CarStatus;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class for mapping between Car DTOs and Entities
 */
public class CarMapper {

    private CarMapper() {
        // Utility class, hide constructor
    }

    /**
     * Maps a CarEntity to a detailed CarDetailsDTO
     */
    public static CarDetailsDTO toDetailsDTO(CarEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return CarDetailsDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .make(entity.getMake())
                .model(entity.getModel())
                .type(entity.getType())
                .year(entity.getYear())
                .condition(entity.getCondition())
                .stockNumber(entity.getStockNumber())
                .vinNumber(entity.getVinNumber())
                .regularPrice(entity.getRegularPrice())
                .salePrice(entity.getSalePrice())
                .requestPrice(entity.getRequestPrice())
                .description(entity.getDescription())
                .priceLabel(entity.isPriceLabel())
                .status(entity.getStatus())
                .build();
    }
    
    /**
     * Maps a CarEntity to a search result DTO
     */
    public static CarSearchResultDTO toSearchResultDTO(CarEntity entity) {
        if (entity == null) {
            return null;
        }
        
        CarSearchResultDTO dto = CarSearchResultDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .make(entity.getMake())
                .model(entity.getModel())
                .type(entity.getType())
                .regularPrice(entity.getRegularPrice())
                .build();
        
        // Add photo URLs if media exists
        if (entity.getMedia() != null) {
            dto.setPhotoUrls(buildPhotoUrls(entity.getId(), entity.getMedia()));
        }
        
        return dto;
    }

    /**
     * Maps a CarEntity to a status DTO for admin views
     */
    public static CarStatusDTO toStatusDTO(CarEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return CarStatusDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .make(entity.getMake())
                .model(entity.getModel())
                .type(entity.getType())
                .status(entity.getStatus())
                .price(entity.getRegularPrice()) // Default to regular price
                .createTime(entity.getCreateTime())
                .updateTime(entity.getUpdateTime())
                .build();
    }
    
    /**
     * Maps a list of CarEntity objects to CarDetailsDTO objects
     */
    public static List<CarDetailsDTO> toDetailsDTOList(List<CarEntity> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        
        return entities.stream()
                .map(CarMapper::toDetailsDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Maps a list of CarEntity objects to CarSearchResultDTO objects
     */
    public static List<CarSearchResultDTO> toSearchResultDTOList(List<CarEntity> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        
        return entities.stream()
                .map(CarMapper::toSearchResultDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Maps a list of CarEntity objects to CarStatusDTO objects
     */
    public static List<CarStatusDTO> toStatusDTOList(List<CarEntity> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        
        return entities.stream()
                .map(CarMapper::toStatusDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Creates a new CarEntity from a CarCreateDTO
     */
    public static CarEntity toEntity(CarCreateDTO dto, VendorEntity vendor) {
        if (dto == null) {
            return null;
        }
        
        CarEntity entity = new CarEntity();
        entity.setTitle(dto.getTitle());
        entity.setMake(dto.getMake());
        entity.setModel(dto.getModel());
        entity.setType(dto.getType());
        entity.setYear(dto.getYear());
        entity.setCondition(dto.getCondition());
        entity.setStockNumber(dto.getStockNumber());
        entity.setVinNumber(dto.getVinNumber());
        entity.setRegularPrice(dto.getRegularPrice());
        entity.setSalePrice(dto.getSalePrice());
        entity.setRequestPrice(dto.getRequestPrice());
        entity.setDescription(dto.getDescription());
        entity.setPriceLabel(dto.isPriceLabel());
        entity.setStatus(CarStatus.PENDING.name()); // Default status
        entity.setVendor(vendor);
        
        // Set timestamps
        Timestamp now = new Timestamp(System.currentTimeMillis());
        entity.setCreateTime(now);
        entity.setUpdateTime(now);

        return entity;
    }

    /**
     * Builds a list of photo URLs from car media entity
     */
    private static List<String> buildPhotoUrls(Long carId, CarMediaEntity media) {
        List<String> photoUrls = new ArrayList<>();

        // Base URL for photo endpoints
        String baseUrl = "/api/cars/media/" + carId + "/photo";

        if (media.getPhoto1() != null) photoUrls.add(baseUrl + "1");
        if (media.getPhoto2() != null) photoUrls.add(baseUrl + "2");
        if (media.getPhoto3() != null) photoUrls.add(baseUrl + "3");
        if (media.getPhoto4() != null) photoUrls.add(baseUrl + "4");
        if (media.getPhoto5() != null) photoUrls.add(baseUrl + "5");

        return photoUrls;
    }

    /**
     * Convert CarDetailsDTO to CarSearchResultDTO for simplified responses
     */
    public static CarSearchResultDTO detailsToSearchResult(CarDetailsDTO detailsDTO) {
        if (detailsDTO == null) {
            return null;
        }

        return CarSearchResultDTO.builder()
                .id(detailsDTO.getId())
                .title(detailsDTO.getTitle())
                .make(detailsDTO.getMake())
                .model(detailsDTO.getModel())
                .type(detailsDTO.getType())
                .regularPrice(detailsDTO.getRegularPrice())
                .build();
    }
}