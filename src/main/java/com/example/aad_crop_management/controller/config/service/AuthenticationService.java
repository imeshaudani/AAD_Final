package com.example.aad_crop_management.controller.config.service;

import com.example.aad_crop_management.controller.config.dto.impl.UserDTO;
import com.example.aad_crop_management.controller.config.jwtModels.JWTResponse;
import com.example.aad_crop_management.controller.config.jwtModels.SignIn;

public interface AuthenticationService {
    JWTResponse signIn(SignIn signIn);
    JWTResponse signUp(UserDTO userDTO);
    JWTResponse refreshToken(String accessToken);
}
