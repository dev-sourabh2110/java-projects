package com.data.repository;

import com.data.entity.CarMediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarMediaRepository extends JpaRepository<CarMediaEntity, Long> {
    @Query("SELECT m FROM CarMediaEntity m WHERE m.car.id = :carId")
    Optional<CarMediaEntity> findByCarId(Long carId);
}

