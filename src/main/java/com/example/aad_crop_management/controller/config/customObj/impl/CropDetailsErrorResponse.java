package com.example.aad_crop_management.controller.config.customObj.impl;

import com.example.aad_crop_management.controller.config.customObj.CropDetailsResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropDetailsErrorResponse implements CropDetailsResponse {
    private int errorCode;
    private String errorMessage;
}
