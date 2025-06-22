package com.data.enums;

public enum CarCondition {
    NEW("New"),
    USED("Used"),
    CERTIFIED("Certified Pre-Owned");
    
    private final String displayName;
    
    CarCondition(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public static CarCondition fromString(String text) {
        for (CarCondition condition : CarCondition.values()) {
            if (condition.name().equalsIgnoreCase(text) || 
                condition.getDisplayName().equalsIgnoreCase(text)) {
                return condition;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }
}