package com.example.aad_crop_management.dto.impl;

import com.example.aad_crop_management.customObj.UserResponse;
import com.example.aad_crop_management.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements SuperDTO, UserResponse {
    private String email;
    private String password;
    private Role role;
}