package com.example.aad_crop_management.service;

import com.example.aad_crop_management.customObj.CropResponse;
import com.example.aad_crop_management.dto.impl.CropDTO;

import java.util.List;

public interface CropService{
    void saveCrop(CropDTO cropDTO);
    List<CropDTO> getAllCrops();
    CropResponse getSelectedCrop(String cropCode);
    void updateCrop(CropDTO updateCropDTO);
    void deleteCrop(String cropCode);
}