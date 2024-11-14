package com.example.aad_crop_management.customObj.impl;

import com.example.aad_crop_management.customObj.CropDetailsResponse;
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