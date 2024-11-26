package com.example.aad_crop_management.controller.config.dao;

import com.example.aad_crop_management.controller.config.entity.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentDao extends JpaRepository<EquipmentEntity,String> {
}