package com.example.aad_crop_management.dto.impl;

import com.example.aad_crop_management.customObj.FieldResponse;
import com.example.aad_crop_management.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StaffDTO implements SuperDTO, FieldResponse {
    private String fieldCode;
    private String fieldName;
    private Point fieldLocation;
    private double extendSize;
    private List<CropDTO> crops;
    private List<StaffDTO> staff;
    private String fieldImage1;
    private String fieldImage2;

}