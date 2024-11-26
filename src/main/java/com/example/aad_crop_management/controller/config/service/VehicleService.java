package com.example.aad_crop_management.controller.config.service;

import com.example.aad_crop_management.controller.config.customObj.VehicleResponse;
import com.example.aad_crop_management.controller.config.dto.impl.VehicleDTO;
import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDTO vehicleDTO);
    List<VehicleDTO> getAllVehicles();
    VehicleResponse getSelectedVehicle(String vehicleCode);
    void updateVehicle(String vehicleCode, VehicleDTO vehicleDTO);
    void deleteVehicle(String vehicleCode);
}
