package com.example.aad_crop_management.dao;

import com.example.aad_crop_management.entity.CropDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropDetailsDao extends JpaRepository<CropDetailsEntity,String> {

}