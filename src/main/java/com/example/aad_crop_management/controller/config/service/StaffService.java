package com.example.aad_crop_management.controller.config.service;

import com.example.aad_crop_management.controller.config.customObj.StaffResponse;
import com.example.aad_crop_management.controller.config.dto.impl.StaffDTO;
import java.util.List;

public interface StaffService {
    void saveStaff(StaffDTO staffDTO);
    List<StaffDTO> getAllStaffs();
    StaffResponse getSelectedStaff(String staffId);
    void updateStaff(String staffId, StaffDTO staffDTO);
    void deleteStaff(String staffId);
}
