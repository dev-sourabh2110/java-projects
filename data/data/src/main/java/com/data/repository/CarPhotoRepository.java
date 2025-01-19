package com.data.repository;

import com.data.entity.CarPhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarPhotoRepository extends JpaRepository<CarPhotoEntity, Long> {}
