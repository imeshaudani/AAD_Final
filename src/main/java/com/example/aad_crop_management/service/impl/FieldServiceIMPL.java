package com.example.aad_crop_management.service.impl;

import com.example.aad_crop_management.customObj.FieldResponse;
import com.example.aad_crop_management.customObj.impl.FieldErrorResponse;
import com.example.aad_crop_management.dto.impl.FieldDTO;
import com.example.aad_crop_management.entity.FieldEntity;
import com.example.aad_crop_management.exception.FieldNotFound;
import com.example.aad_crop_management.service.FieldService;
import com.example.aad_crop_management.utill.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;
import java.util.Optional;

@Service
public class FieldServiceIMPL implements FieldService {
    @Autowired
    private FieldDao fieldDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveField(FieldDTO fieldDTO) {
        FieldEntity fieldEntity = mapping.convertToFieldEntity(fieldDTO);

        if (fieldEntity.getFieldCode() == null || fieldEntity.getFieldCode().isEmpty()){
            fieldEntity.setFieldCode(AppUtil.createFieldId());
        }
        fieldDao.save(fieldEntity);
    }

    @Override
    public List<FieldDTO> getAllFields() {
        List<FieldEntity> getAllFields = fieldDao.findAll();
        return mapping.convertFieldToDTOList(getAllFields);
    }

    @Override
    public FieldResponse getSelectedField(String fieldCode) {
        if (fieldDao.existsById(fieldCode)) {
            FieldEntity fieldEntityByFieldCode = fieldDao.getReferenceById(fieldCode);
            return mapping.convertToFieldDTO(fieldEntityByFieldCode);
        } else {
            return new FieldErrorResponse(0, "Field not Found");
        }
    }

    @Override
    public void updateField(FieldDTO updateFieldDTO) {
        Optional<FieldEntity> tmpField = fieldDao.findById(updateFieldDTO.getFieldCode());
        if (!tmpField.isPresent()){
            throw new FieldNotFound("Field not Found");
        } else {
            FieldEntity fieldEntity = tmpField.get();
            fieldEntity.setFieldName(updateFieldDTO.getFieldName());
            fieldEntity.setFieldLocation(updateFieldDTO.getFieldLocation());
            fieldEntity.setExtendSize(updateFieldDTO.getExtendSize());
            fieldEntity.setFieldImage1(updateFieldDTO.getFieldImage1());
            fieldEntity.setFieldImage1(updateFieldDTO.getFieldImage2());

            fieldDao.save(fieldEntity);
        }
    }

    @Override
    public void deleteField(String fieldCode) {
        Optional<FieldEntity> findId = fieldDao.findById(fieldCode);
        if (!findId.isPresent()){
            throw new FieldNotFound("Field not Found");
        }else {
            fieldDao.deleteById(fieldCode);
        }
    }
}