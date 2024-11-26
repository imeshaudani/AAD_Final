package com.example.aad_crop_management.controller.config.customObj.impl;

import com.example.aad_crop_management.controller.config.customObj.VehicleResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleErrorResponse implements VehicleResponse, Serializable {
    private int errorCode;
    private String errorMessage;
}
