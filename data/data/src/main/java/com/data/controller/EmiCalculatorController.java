package com.data.controller;

import com.data.service.EmiCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/emi")
public class EmiCalculatorController {
    @Autowired
    private EmiCalculatorService emiCalculatorService;

    @GetMapping
    public ResponseEntity<Double> calculateEmi(
            @RequestParam double principal, @RequestParam double rate, @RequestParam int tenure) {
        double emi = emiCalculatorService.calculateEmi(principal, rate, tenure);
        return ResponseEntity.ok(emi);
    }
}
