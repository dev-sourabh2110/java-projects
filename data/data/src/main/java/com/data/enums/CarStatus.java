package com.data.enums;

/**
 * Enumeration of possible car listing statuses in the system.
 * Using enum ensures type safety and prevents invalid status values.
 */
public enum CarStatus {
    PENDING("Pending"),
    APPROVED("Approved"),
    REJECTED("Rejected"),
    SOLD("Sold"),
    RESERVED("Reserved"),
    INACTIVE("Inactive");

    private final String displayName;

    CarStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    /**
     * Safely convert a string to CarStatus enum
     * @param text The string representation of the status
     * @return The matching CarStatus enum value
     * @throws IllegalArgumentException if no matching status is found
     */
    public static CarStatus fromString(String text) {
        if (text == null || text.isEmpty()) {
            return PENDING; // Default value if none provided
        }

        for (CarStatus status : CarStatus.values()) {
            if (status.name().equalsIgnoreCase(text) ||
                    status.getDisplayName().equalsIgnoreCase(text)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid car status: " + text);
    }

    /**
     * Safely get a status without throwing exceptions
     * @param text The string representation of the status
     * @return The matching CarStatus or PENDING if not found
     */
    public static CarStatus safeValueOf(String text) {
        try {
            return fromString(text);
        } catch (IllegalArgumentException e) {
            return PENDING;
        }
    }

    @Override
    public String toString() {
        return displayName;
    }
}