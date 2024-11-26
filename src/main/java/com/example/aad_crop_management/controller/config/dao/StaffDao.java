package com.example.aad_crop_management.controller.config.dao;

import com.example.aad_crop_management.controller.config.entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffDao extends JpaRepository<StaffEntity,String> {
}
