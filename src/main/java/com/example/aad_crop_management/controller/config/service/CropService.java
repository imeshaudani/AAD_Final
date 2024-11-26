package com.example.aad_crop_management.controller.config.service;

import com.example.aad_crop_management.controller.config.customObj.CropResponse;
import com.example.aad_crop_management.controller.config.dto.impl.CropDTO;
import java.util.List;

public interface CropService {
    void saveCrop(CropDTO cropDTO);
    List<CropDTO> getAllCrops();
    CropResponse getSelectedCrop(String cropCode);
    void updateCrop(CropDTO updateCropDTO);
    void deleteCrop(String cropCode);
}
