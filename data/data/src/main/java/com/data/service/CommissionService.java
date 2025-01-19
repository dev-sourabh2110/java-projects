package com.data.service;

import com.data.entity.CommissionEntity;
import com.data.repository.CommissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommissionService {
    @Autowired
    private CommissionRepository commissionRepository;

    public String setCommission(String category, Double value, boolean isPercentage) {
        CommissionEntity commission = commissionRepository.findByCarCategory(category)
                .orElse(new CommissionEntity());
        commission.setCarCategory(category);
        commission.setValue(value);
        commission.setPercentage(isPercentage);

        commissionRepository.save(commission);
        return "Commission updated successfully.";
    }
}

