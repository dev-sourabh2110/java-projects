package com.data.repository;

import com.data.entity.CarFeaturesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarFeaturesRepository extends JpaRepository<CarFeaturesEntity, Long> {
    @Query("SELECT f FROM CarFeaturesEntity f WHERE f.car.id = :carId")
    Optional<CarFeaturesEntity> findByCarId(Long carId);
}

