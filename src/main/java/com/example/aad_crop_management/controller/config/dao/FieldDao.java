package com.example.aad_crop_management.controller.config.dao;

import com.example.aad_crop_management.controller.config.entity.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldDao extends JpaRepository<FieldEntity,String> {
}
