package com.data.dto.car;

import java.io.Serializable;

/**
 * Base DTO containing common car properties
 * All car DTOs will inherit from this class
 */
public abstract class BaseCarDTO implements Serializable {
    private Long id;
    private String title;
    private String make;
    private String model;
    private String type;
    
    // Protected constructor for inheritance
    protected BaseCarDTO() {
    }
    
    // Protected constructor with common fields
    protected BaseCarDTO(Long id, String title, String make, String model, String type) {
        this.id = id;
        this.title = title;
        this.make = make;
        this.model = model;
        this.type = type;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}