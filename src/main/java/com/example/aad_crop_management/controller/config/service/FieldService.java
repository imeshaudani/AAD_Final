package com.example.aad_crop_management.controller.config.service;

import com.example.aad_crop_management.controller.config.customObj.FieldResponse;
import com.example.aad_crop_management.controller.config.dto.impl.FieldDTO;
import java.util.List;

public interface FieldService {
    void saveField(FieldDTO fieldDTO);
    List<FieldDTO> getAllFields();
    FieldResponse getSelectedField(String fieldCode);
    void updateField(FieldDTO updateFieldDTO);
    void deleteField(String fieldCode);
}
