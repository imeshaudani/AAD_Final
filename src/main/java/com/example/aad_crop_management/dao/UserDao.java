package com.example.aad_crop_management.dao;

import com.example.aad_crop_management.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserDao extends JpaRepository<UserEntity,String> {
}