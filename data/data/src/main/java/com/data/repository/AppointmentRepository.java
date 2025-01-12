package com.data.repository;

import com.data.entity.Appointment;
import com.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByTestDriveEmail(String user);
//    List<Appointment> findByUserEmail(String email);
}
