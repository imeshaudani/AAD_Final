package com.example.aad_crop_management.dto.impl;

import com.example.aad_crop_management.customObj.EquipmentResponse;
import com.example.aad_crop_management.dto.SuperDTO;

public class EquipmentDTO implements SuperDTO, EquipmentResponse {
    private String equipmentId;
    private String name;
    private String type;
    private String status;
    private String staffId;
    private String fieldCode;
}