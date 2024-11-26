package com.example.aad_crop_management.controller.config.customObj.impl;

import com.example.aad_crop_management.controller.config.customObj.FieldResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FieldErrorResponse implements FieldResponse, Serializable {
    private int errorCode;
    private String errorMessage;
}
