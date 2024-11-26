package com.example.aad_crop_management.controller.config.service;

import com.example.aad_crop_management.controller.config.customObj.UserResponse;
import com.example.aad_crop_management.controller.config.dto.impl.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserResponse getSelectedUser(String email);
    void updateUser(String email, UserDTO userDTO);
    void deleteUser(String email);
    UserDetailsService userDetailsService();
}
