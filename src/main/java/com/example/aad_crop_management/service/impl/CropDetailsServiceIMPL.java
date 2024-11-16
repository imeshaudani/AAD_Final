package com.example.aad_crop_management.service.impl;

import com.example.aad_crop_management.customObj.CropDetailsResponse;
import com.example.aad_crop_management.customObj.impl.CropDetailsErrorResponse;
import com.example.aad_crop_management.dao.CropDao;
import com.example.aad_crop_management.dao.CropDetailsDao;
import com.example.aad_crop_management.dao.FieldDao;
import com.example.aad_crop_management.dao.StaffDao;
import com.example.aad_crop_management.dto.impl.CropDetailsDTO;
import com.example.aad_crop_management.entity.CropDetailsEntity;
import com.example.aad_crop_management.entity.CropEntity;
import com.example.aad_crop_management.entity.FieldEntity;
import com.example.aad_crop_management.entity.StaffEntity;
import com.example.aad_crop_management.service.CropDetailsService;
import com.example.aad_crop_management.utill.AppUtil;
import com.example.aad_crop_management.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class CropDetailsServiceIMPL implements CropDetailsService {
    @Autowired
    private CropDetailsDao cropDetailsDao;

    @Autowired
    private CropDao cropDao;

    @Autowired
    private StaffDao staffDao;

    @Autowired
    private FieldDao fieldDao;

    @Autowired
    private Mapping mapping;

    @Override
    @Transactional
    public void saveCropDetails(CropDetailsDTO cropDetailsDTO) {
        CropDetailsEntity cropDetailsEntity = mapping.convertToCropDetailsEntity(cropDetailsDTO);

        if (cropDetailsEntity.getLogCode() == null || cropDetailsEntity.getLogCode().isEmpty()){
            cropDetailsEntity.setLogCode(AppUtil.createLogCode());
        }

        List<FieldEntity> fields = getFieldsFromCodes(cropDetailsDTO.getFieldCodes());
        List<CropEntity> crops = getCropsFromCodes(cropDetailsDTO.getCropCodes());
        List<StaffEntity> staff = getStaffFromIds(cropDetailsDTO.getStaffIds());

        cropDetailsEntity.setField(fields);
        cropDetailsEntity.setCrop(crops);
        cropDetailsEntity.setStaff(staff);

        cropDetailsDao.save(cropDetailsEntity);
    }

    @Override
    public List<CropDetailsDTO> getAllCropDetails() {
        List<CropDetailsEntity> getAllCropDetails = cropDetailsDao.findAll();
        return mapping.convertCropDetailsToDTOList(getAllCropDetails);
    }

    @Override
    public CropDetailsResponse getSelectedCropDetail(String logCode) {
        if (cropDetailsDao.existsById(logCode)) {
            CropDetailsEntity cropDetailsEntityByLogCode = cropDetailsDao.getReferenceById(logCode);
            return mapping.convertToCropDetailsDTO(cropDetailsEntityByLogCode);
        } else {
            return new CropDetailsErrorResponse(0, "Crop Details not Found");
        }
    }

    @Override
    public void updateCropDetails(CropDetailsDTO updatecropDetailsDTO) {
        Optional<CropDetailsEntity> existingEntityOptional = cropDetailsDao.findById(updatecropDetailsDTO.getLogCode());

        if (existingEntityOptional.isPresent()) {
            CropDetailsEntity existingEntity = existingEntityOptional.get();

            existingEntity.setLogDetails(updatecropDetailsDTO.getLogDetails());
            existingEntity.setObservedImage(updatecropDetailsDTO.getObservedImage());


            if (updatecropDetailsDTO.getFieldCodes() != null) {
                List<FieldEntity> fieldEntities = fieldDao.findAllById(updatecropDetailsDTO.getFieldCodes());
                existingEntity.setField(fieldEntities);
            }

            if (updatecropDetailsDTO.getCropCodes() != null) {
                List<CropEntity> cropEntities = cropDao.findAllById(updatecropDetailsDTO.getCropCodes());
                existingEntity.setCrop(cropEntities);
            }

            if (updatecropDetailsDTO.getStaffIds() != null) {
                List<StaffEntity> staffEntities = staffDao.findAllById(updatecropDetailsDTO.getStaffIds());
                existingEntity.setStaff(staffEntities);
            }

            cropDetailsDao.save(existingEntity);
        } else {
            throw new CropDetailsNotFound("Crop details with logCode " + updatecropDetailsDTO.getLogCode() + " not found");
        }
    }

    @Override
    public void deleteCropDetails(String logCode) {
        Optional<CropDetailsEntity> findId = cropDetailsDao.findById(logCode);
        if (!findId.isPresent()){
            throw new CropDetailsNotFound("Crop Details not Found");
        }else {
            cropDetailsDao.deleteById(logCode);
        }
    }


    private List<FieldEntity> getFieldsFromCodes(List<String> fieldCodes){
        return fieldDao.findAllById(fieldCodes);
    }

    private List<CropEntity> getCropsFromCodes(List<String> cropCodes){
        return cropDao.findAllById(cropCodes);
    }

    private List<StaffEntity> getStaffFromIds(List<String> staffIds){
        return staffDao.findAllById(staffIds);
    }
}