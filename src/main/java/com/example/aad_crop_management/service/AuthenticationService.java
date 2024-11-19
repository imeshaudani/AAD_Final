package com.example.aad_crop_management.service;

import com.example.aad_crop_management.dto.impl.UserDTO;
import com.example.aad_crop_management.jwtModels.JWTResponse;
import com.example.aad_crop_management.jwtModels.SignIn;

public interface AuthenticationService {
    JWTResponse signIn(SignIn signIn);
    JWTResponse signUp(UserDTO userDTO);
    JWTResponse refreshToken(String accessToken);
}
