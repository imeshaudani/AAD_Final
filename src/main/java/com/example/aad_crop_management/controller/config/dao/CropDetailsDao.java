package com.example.aad_crop_management.controller.config.dao;

import com.example.aad_crop_management.controller.config.entity.CropDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropDetailsDao extends JpaRepository<CropDetailsEntity,String> {

}
