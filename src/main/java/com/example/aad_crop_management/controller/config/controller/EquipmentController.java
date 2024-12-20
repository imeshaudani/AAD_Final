
package com.example.aad_crop_management.controller.config.controller;

import com.example.aad_crop_management.controller.config.customObj.EquipmentResponse;
import com.example.aad_crop_management.controller.config.dto.impl.EquipmentDTO;
import com.example.aad_crop_management.controller.config.exception.DataPersistFailedException;
import com.example.aad_crop_management.controller.config.exception.EquipmentNotFound;
import com.example.aad_crop_management.controller.config.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/equipment")
@CrossOrigin("*")
@RequiredArgsConstructor
public class EquipmentController {
    @Autowired
    private final EquipmentService equipmentService;

    static Logger logger = LoggerFactory.getLogger(EquipmentController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createEquipment(@RequestBody EquipmentDTO equipment){
        if (equipment == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            try {
                equipmentService.saveEquipment(equipment);
                logger.info("Equipment saved :" + equipment);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }catch (DataPersistFailedException e){
                logger.error(e.getMessage());
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }catch (Exception e){
                logger.error(e.getMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping(value = "allequipment" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EquipmentDTO> getAllEquipments(){
        return equipmentService.getAllEquipments();
    }

    @GetMapping(value = "/{equipmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EquipmentResponse getSelectedEquipment(@PathVariable("equipmentId") String equipmentId){
        return equipmentService.getSelectedEquipment(equipmentId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{equipmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateEquipment(@PathVariable("equipmentId") String equipmentId, @RequestBody EquipmentDTO equipment){
        try{
            if (equipment == null && (equipmentId == null || equipment.equals(""))){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            equipmentService.updateEquipment(equipmentId,equipment);
            logger.info("Equipment Updated :" + equipment);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (EquipmentNotFound e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{equipmentId}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable("equipmentId") String equipmentId){
        try{
            equipmentService.deleteEquipment(equipmentId);
            logger.info("Equipment deleted :" + equipmentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (EquipmentNotFound e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
