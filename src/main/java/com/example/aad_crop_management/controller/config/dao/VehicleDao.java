package com.example.aad_crop_management.controller.config.dao;

import com.example.aad_crop_management.controller.config.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleDao extends JpaRepository<VehicleEntity,String> {
}
