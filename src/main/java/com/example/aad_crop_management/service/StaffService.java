package com.example.aad_crop_management.service;

import com.example.aad_crop_management.customObj.StaffResponse;
import com.example.aad_crop_management.dto.impl.StaffDTO;

import java.util.List;

public interface StaffService {
    void saveStaff(StaffDTO staffDTO);
    List<StaffDTO> getAllStaffs();
    StaffResponse getSelectedStaff(String staffId);
    void updateStaff(String staffId, StaffDTO staffDTO);
    void deleteStaff(String staffId);
}
