
package com.example.aad_crop_management.controller.config.dto.impl;

import com.example.aad_crop_management.controller.config.customObj.EquipmentResponse;
import com.example.aad_crop_management.controller.config.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDTO implements SuperDTO, EquipmentResponse {
    private String equipmentId;
    private String name;
    private String type;
    private String status;
    private String staffId;
    private String fieldCode;
}
