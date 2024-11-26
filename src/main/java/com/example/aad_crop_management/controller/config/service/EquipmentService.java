package com.example.aad_crop_management.controller.config.service;

import com.example.aad_crop_management.controller.config.customObj.EquipmentResponse;
import com.example.aad_crop_management.controller.config.dto.impl.EquipmentDTO;
import java.util.List;

public interface EquipmentService {
    void saveEquipment(EquipmentDTO equipmentDTO);
    List<EquipmentDTO> getAllEquipments();
    EquipmentResponse getSelectedEquipment(String equipmentId);
    void updateEquipment(String equipmentId, EquipmentDTO equipmentDTO);
    void deleteEquipment(String equipmentId);
}