package com.example.aad_crop_management.controller.config.controller;

import com.example.aad_crop_management.controller.config.customObj.FieldResponse;
import com.example.aad_crop_management.controller.config.dto.impl.FieldDTO;
import com.example.aad_crop_management.controller.config.exception.FieldNotFound;
import com.example.aad_crop_management.controller.config.service.FieldService;
import com.example.aad_crop_management.controller.config.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/field")
@CrossOrigin("*")
@RequiredArgsConstructor
public class FieldController {
    @Autowired
    private final FieldService fieldService;
    static Logger logger = LoggerFactory.getLogger(FieldController.class);
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveField(
            @RequestParam("fieldName") String fieldName,
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            @RequestParam("extentSize") Double extentSize,
            @RequestParam("fieldImage1") MultipartFile fieldImage1,
            @RequestParam("fieldImage2") MultipartFile fieldImage2,
            @RequestParam("staffIds") String staffIds
    ){
        try{
            List<String> staffIdList = Arrays.asList(staffIds.split(","));
//            Point fieldLocation = new Point(Double.parseDouble(latitude), Double.parseDouble(longitude));
            String base64FieldImage1 = AppUtil.toBase64FieldImage1(fieldImage1);
            String base64FieldImage2 = AppUtil.toBase64FieldImage2(fieldImage2);
            var fieldDTO = new FieldDTO();
            fieldDTO.setFieldCode(AppUtil.createFieldId());
            fieldDTO.setFieldName(fieldName);
            fieldDTO.setFieldLocation(new Point((int) latitude, (int)longitude));
            fieldDTO.setExtendSize((extentSize));
            fieldDTO.setFieldImage1(base64FieldImage1);
            fieldDTO.setFieldImage2(base64FieldImage2);
            fieldDTO.setStaffId(staffIdList);

            fieldService.saveField(fieldDTO);
            logger.info("Field saved :" + fieldDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (FieldNotFound e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "allfield" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FieldDTO> getAllFields(){
        return fieldService.getAllFields();
    }

    @GetMapping(value = "/{fieldCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public FieldResponse getSelectedField(@PathVariable("fieldCode") String fieldCode){
        return fieldService.getSelectedField(fieldCode);
    }

    @PatchMapping(value = "/{fieldCode}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateField(
            @PathVariable("fieldCode") String fieldCode,
            @RequestPart("fieldName") String fieldName,
            @RequestPart("latitude") String latitude,
            @RequestPart("longitude") String longitude,
            @RequestPart("extentSize") String extentSize,
            @RequestPart("fieldImage1") MultipartFile fieldImage1,
            @RequestPart("fieldImage2") MultipartFile fieldImage2,
            @RequestPart("staffIds") String staffIds
    ) {
        try {
            List<String> staffIdList = Arrays.asList(staffIds.split(","));
            String updateBase64FieldImage1 = null;
            String updateBase64FieldImage2 = null;

            if (fieldImage1 != null && !fieldImage1.isEmpty()) {
                updateBase64FieldImage1 = AppUtil.toBase64FieldImage1(fieldImage1);
            }

            if (fieldImage2 != null && !fieldImage2.isEmpty()) {
                updateBase64FieldImage2 = AppUtil.toBase64FieldImage2(fieldImage2);
            }

            FieldDTO updateFieldDTO = new FieldDTO();
            updateFieldDTO.setFieldCode(fieldCode);
            updateFieldDTO.setFieldName(fieldName);
            updateFieldDTO.setFieldLocation(new Point(Double.parseDouble(latitude), Double.parseDouble(longitude)));
            updateFieldDTO.setExtendSize(Double.parseDouble(extentSize));
            updateFieldDTO.setStaffId(staffIdList);

            if (updateBase64FieldImage1 != null) {
                updateFieldDTO.setFieldImage1(updateBase64FieldImage1);
            }

            if (updateBase64FieldImage2 != null) {
                updateFieldDTO.setFieldImage2(updateBase64FieldImage2);
            }

            fieldService.updateField(updateFieldDTO);
            logger.info("Field Updated :" + updateFieldDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (FieldNotFound e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping(value = "/{fieldCode}")
    public ResponseEntity<Void> deleteField(@PathVariable("fieldCode") String fieldCode){
        try{
            fieldService.deleteField(fieldCode);
            logger.info("Field deleted :" + fieldCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (FieldNotFound e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

