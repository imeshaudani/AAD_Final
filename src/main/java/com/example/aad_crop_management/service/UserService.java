package com.example.aad_crop_management.service;

import com.example.aad_crop_management.customObj.UserResponse;
import com.example.aad_crop_management.dto.impl.UserDTO;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserResponse getSelectedUser(String email);
    void updateUser(String email, UserDTO userDTO);
    void deleteUser(String email);
}