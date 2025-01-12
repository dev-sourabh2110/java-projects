package com.data.repository;

import com.data.entity.PurchasedCarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchasedCarRepository extends JpaRepository<PurchasedCarEntity, Long> {
    List<PurchasedCarEntity> findByUserEmail(String email);
}

