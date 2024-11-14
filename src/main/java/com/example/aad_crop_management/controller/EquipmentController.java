package com.example.aad_crop_management.controller;

import com.example.aad_crop_management.customObj.EquipmentResponse;
import com.example.aad_crop_management.dto.impl.EquipmentDTO;
import com.example.aad_crop_management.exception.DataPersistFailedException;
import com.example.aad_crop_management.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/equipment")
@RequiredArgsConstructor
public class EquipmentController {
    @Autowired
    private final EquipmentService equipmentService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createEquipment(@RequestBody EquipmentDTO equipment){
        if (equipment == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            try {
                equipmentService.saveEquipment(equipment);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }catch (DataPersistFailedException e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EquipmentDTO> getAllEquipments(){
        return equipmentService.getAllEquipments();
    }

    @GetMapping(value = "/{equipmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EquipmentResponse getSelectedEquipment(@PathVariable("equipmentId") String equipmentId){
        return equipmentService.getSelectedEquipment(equipmentId);
    }
}
