package com.example.aad_crop_management.service;

import com.example.aad_crop_management.customObj.CropDetailsResponse;
import com.example.aad_crop_management.dto.impl.CropDetailsDTO;

import java.util.List;

public interface CropDetailsService {
    void saveCropDetails(CropDetailsDTO cropDetailsDTO);
    List<CropDetailsService> getAllCropDetails();
    CropDetailsResponse getSelectedCropDetail(String logCode);
    void updateCropDetails(CropDetailsDTO updatecropDetailsDTO);
    void deleteCropDetails(String logCode);
}