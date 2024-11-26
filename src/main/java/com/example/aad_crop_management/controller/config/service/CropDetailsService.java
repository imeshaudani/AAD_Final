package com.example.aad_crop_management.controller.config.service;

import com.example.aad_crop_management.controller.config.customObj.CropDetailsResponse;
import com.example.aad_crop_management.controller.config.dto.impl.CropDetailsDTO;

import java.util.List;

public interface CropDetailsService {
    void saveCropDetails(CropDetailsDTO cropDetailsDTO);
    List<CropDetailsDTO> getAllCropDetails();
    CropDetailsResponse getSelectedCropDetail(String logCode);
    void updateCropDetails(CropDetailsDTO updatecropDetailsDTO);
    void deleteCropDetails(String logCode);
}
