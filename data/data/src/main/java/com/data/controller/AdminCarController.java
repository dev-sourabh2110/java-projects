package com.data.controller;

import com.data.dto.CarResponseDTO;
import com.data.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/cars")
public class AdminCarController {

    private final CarService carService;

    public AdminCarController(CarService carService) {
        this.carService = carService;
    }

    /**
     * Get all cars by status
     */
    @Secured("ROLE_ADMIN")
    @GetMapping
    public ResponseEntity<List<CarResponseDTO>> getCarsByStatus(@RequestParam String status) {
        return ResponseEntity.ok(carService.getCarsByStatus(status));
    }

    /**
     * Approve a car listing
     */
    @Secured("ROLE_ADMIN")
    @PostMapping("/{carId}/approve")
    public ResponseEntity<String> approveCar(@PathVariable Long carId) {
        carService.approveCar(carId);
        return ResponseEntity.ok("Car approved successfully");
    }

    /**
     * Reject a car listing
     */
    @Secured("ROLE_ADMIN")
    @PostMapping("/{carId}/reject")
    public ResponseEntity<String> rejectCar(@PathVariable Long carId) {
        carService.rejectCar(carId);
        return ResponseEntity.ok("Car rejected successfully");
    }

    /**
     * Delete a car listing
     */
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{carId}")
    public ResponseEntity<String> deleteCar(@PathVariable Long carId) {
        carService.deleteCar(carId);
        return ResponseEntity.ok("Car deleted successfully");
    }
}
