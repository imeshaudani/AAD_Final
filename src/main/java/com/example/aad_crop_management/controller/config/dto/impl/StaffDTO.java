package com.example.aad_crop_management.controller.config.dto.impl;

import com.example.aad_crop_management.controller.config.customObj.StaffResponse;
import com.example.aad_crop_management.controller.config.dto.SuperDTO;
import com.example.aad_crop_management.controller.config.enums.Designation;
import com.example.aad_crop_management.controller.config.enums.Gender;
import com.example.aad_crop_management.controller.config.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDTO implements SuperDTO, StaffResponse {
    private String staffId;
    private String firstName;
    private String lastName;
    private Designation designation;
    private Gender gender;
    private LocalDate joinedDate;
    private LocalDate dob;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;
    private String contactNo;
    private String email;
    private Role role;
}
