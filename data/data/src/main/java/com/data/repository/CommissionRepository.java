package com.data.repository;

import com.data.entity.CommissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommissionRepository extends JpaRepository<CommissionEntity, Long> {
    Optional<CommissionEntity> findByCarCategory(String carCategory);
}

