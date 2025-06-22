package com.data.controller;

import com.data.entity.CarEntity;
import com.data.service.SearchService;
import com.data.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    @Autowired
    private SearchService searchService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> searchCars(@RequestParam String query) {
        try {
            List<CarEntity> cars = searchService.searchCars(query);
            return ApiResponse.success(cars, "Search results retrieved successfully");
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
        }
    }
}