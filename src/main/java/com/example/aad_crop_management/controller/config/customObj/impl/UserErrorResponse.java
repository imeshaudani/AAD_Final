package com.example.aad_crop_management.controller.config.customObj.impl;

import com.example.aad_crop_management.controller.config.customObj.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserErrorResponse implements UserResponse, Serializable {
    private int errorCode;
    private String errorMessage;
}
