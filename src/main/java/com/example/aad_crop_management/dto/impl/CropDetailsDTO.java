package com.example.aad_crop_management.dto.impl;

import com.example.aad_crop_management.customObj.CropDetailsResponse;
import com.example.aad_crop_management.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CropDetailsDTO implements SuperDTO, CropDetailsResponse {
    private String logCode;
    private LocalDate logDate;
    private String logDetails;
    private String observedImage;
    private List<String> fieldCodes;
    private List<String> cropCodes;
    private List<String> staffIds;
}