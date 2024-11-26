package com.example.aad_crop_management.controller.config.dto.impl;

import com.example.aad_crop_management.controller.config.customObj.CropResponse;
import com.example.aad_crop_management.controller.config.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropDTO implements SuperDTO, CropResponse {
    private String cropCode;
    private String cropCommonName;
    private String cropScientificName;
    private String cropImage;
    private String category;
    private String cropSeason;
    private String fieldCode;
}
