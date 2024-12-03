package com.example.aad_crop_management.controller.config.entity;

import com.example.aad_crop_management.controller.config.enums.Designation;
import com.example.aad_crop_management.controller.config.enums.Gender;
import com.example.aad_crop_management.controller.config.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "staff")
public class StaffEntity implements SuperEntity{
    @Id
    private String staffId;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Designation designation;
    @Enumerated(EnumType.STRING)
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
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(mappedBy = "staff")
    private List<FieldEntity> feild;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    private List<VehicleEntity> vehicle;
}
