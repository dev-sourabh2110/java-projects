package com.data.service;

import org.springframework.stereotype.Service;

@Service
public class EmiCalculatorService {
    public double calculateEmi(double principal, double rate, int tenure) {
        rate = rate / (12 * 100); // monthly interest rate
        double emi = (principal * rate * Math.pow(1 + rate, tenure)) /
                (Math.pow(1 + rate, tenure) - 1);
        return emi;
    }
}

