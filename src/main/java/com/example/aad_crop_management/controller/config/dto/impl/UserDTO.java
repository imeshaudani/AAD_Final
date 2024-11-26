package com.example.aad_crop_management.controller.config.dto.impl;

import com.example.aad_crop_management.controller.config.customObj.UserResponse;
import com.example.aad_crop_management.controller.config.dto.SuperDTO;
import com.example.aad_crop_management.controller.config.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements SuperDTO, UserResponse {
    private String email;
    private String password;
    private Role role;
}
