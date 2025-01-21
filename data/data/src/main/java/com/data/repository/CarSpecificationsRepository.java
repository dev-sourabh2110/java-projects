package com.data.repository;

import com.data.entity.CarSpecificationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarSpecificationsRepository extends JpaRepository<CarSpecificationsEntity, Long> {
    @Query("SELECT s FROM CarSpecificationsEntity s WHERE s.car.id = :carId")
    Optional<CarSpecificationsEntity> findByCarId(Long carId);
}

