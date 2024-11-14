package com.example.aad_crop_management.service;

import com.example.aad_crop_management.customObj.FieldResponse;
import com.example.aad_crop_management.dto.impl.FieldDTO;

import java.util.List;

public interface FieldService {
    void saveField(FieldDTO fieldDTO);
    List<FieldDTO> getAllFields();
    FieldResponse getSelectedField(String fieldCode);
    void updateField(FieldDTO updateFieldDTO);
    void deleteField(String fieldCode);
}