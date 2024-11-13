package com.example.aad_crop_management.customObj.impl;

import com.example.aad_crop_management.customObj.CropResponse;

import java.io.Serializable;

public class CropErrorResponse implements CropResponse, Serializable {
    private int errorCode;
    private String errorMessage;

}
