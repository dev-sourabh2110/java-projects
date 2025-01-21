package com.data.repository;

import com.data.entity.CarAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarAddressRepository extends JpaRepository<CarAddressEntity, Long> {
    @Query("SELECT a FROM CarAddressEntity a WHERE a.car.id = :carId")
    Optional<CarAddressEntity> findByCarId(Long carId);
}

