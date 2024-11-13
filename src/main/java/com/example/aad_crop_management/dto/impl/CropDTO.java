package com.example.aad_crop_management.dto.impl;

import com.example.aad_crop_management.customObj.CropResponse;
import com.example.aad_crop_management.dto.SuperDTO;

public class CropDTO implements SuperDTO, CropResponse {
    private String cropCode;
    private String cropCommonName;
    private String cropScientificName;
    private String cropImage;
    private String category;
    private String cropSeason;
    private String fieldCode;
}
