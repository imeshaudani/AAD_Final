package com.example.aad_crop_management.controller.config.dto.impl;

import com.example.aad_crop_management.controller.config.customObj.VehicleResponse;
import com.example.aad_crop_management.controller.config.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDTO implements SuperDTO, VehicleResponse {
    private String vehicleCode;
    private String licensePlateNumber;
    private String vehicleCategory;
    private String fuelType;
    private String status;
    private String staffId;
    private String remarks;
}