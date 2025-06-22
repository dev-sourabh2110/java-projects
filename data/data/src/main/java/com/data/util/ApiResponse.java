
package com.data.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class to create standardized API responses
 */
public class ApiResponse {
    
    /**
     * Creates a success response with data
     * 
     * @param data The data to include in the response
     * @param message Success message
     * @return ResponseEntity with standardized format
     */
    public static <T> ResponseEntity<Map<String, Object>> success(T data, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", message);
        response.put("data", data);
        return ResponseEntity.ok(response);
    }
    
    /**
     * Creates a success response with just a message
     * 
     * @param message Success message
     * @return ResponseEntity with standardized format
     */
    public static ResponseEntity<Map<String, Object>> success(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", message);
        return ResponseEntity.ok(response);
    }
    
    /**
     * Creates an error response with custom HTTP status
     * 
     * @param message Error message
     * @param status HTTP status code
     * @return ResponseEntity with standardized format
     */
    public static ResponseEntity<Map<String, Object>> error(String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", message);
        return ResponseEntity.status(status).body(response);
    }
    
    /**
     * Creates a bad request error response (400)
     * 
     * @param message Error message
     * @return ResponseEntity with standardized format
     */
    public static ResponseEntity<Map<String, Object>> badRequest(String message) {
        return error(message, HttpStatus.BAD_REQUEST);
    }
    
    /**
     * Creates a not found error response (404)
     * 
     * @param message Error message
     * @return ResponseEntity with standardized format
     */
    public static ResponseEntity<Map<String, Object>> notFound(String message) {
        return error(message, HttpStatus.NOT_FOUND);
    }
    
    /**
     * Creates an error response from an exception
     * 
     * @param e The exception
     * @param status HTTP status code
     * @return ResponseEntity with standardized format
     */
    public static ResponseEntity<Map<String, Object>> exception(Exception e, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", e.getMessage());
        response.put("error", e.getClass().getSimpleName());
        return ResponseEntity.status(status).body(response);
    }

    /**
     * Creates an error response from an exception with stack trace
     */
    public static ResponseEntity<Map<String, Object>> detailedError(Exception e, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", e.getMessage());
        response.put("error", e.getClass().getSimpleName());

        // Add stack trace for detailed debugging
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        response.put("stackTrace", sw.toString());

        return ResponseEntity.status(status).body(response);
    }
}