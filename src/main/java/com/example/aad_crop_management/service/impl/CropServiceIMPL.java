package com.example.aad_crop_management.service.impl;

import com.example.aad_crop_management.customObj.CropResponse;
import com.example.aad_crop_management.customObj.impl.CropErrorResponse;
import com.example.aad_crop_management.dao.CropDao;
import com.example.aad_crop_management.dto.impl.CropDTO;
import com.example.aad_crop_management.entity.CropEntity;
import com.example.aad_crop_management.exception.CropNotFound;
import com.example.aad_crop_management.service.CropService;
import com.example.aad_crop_management.utill.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;
import java.util.Optional;

@Service
public class CropServiceIMPL implements CropService {
    @Autowired
    private CropDao cropDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveCrop(CropDTO cropDTO) {
        CropEntity cropEntity = mapping.convertToCropEntity(cropDTO);

        if (cropEntity.getCropCode() == null || cropEntity.getCropCode().isEmpty()){
            cropEntity.setCropCode(AppUtil.createCropId());
        }

        cropDao.save(cropEntity);
    }

    @Override
    public List<CropDTO> getAllCrops() {
        List<CropEntity> getAllCrops = cropDao.findAll();
        return mapping.convertCropToDTOList(getAllCrops);
    }

    @Override
    public CropResponse getSelectedCrop(String cropCode) {
        if (cropDao.existsById(cropCode)) {
            CropEntity cropEntityByCropCode = cropDao.getReferenceById(cropCode);
            return mapping.convertToCropDTO(cropEntityByCropCode);
        } else {
            return new CropErrorResponse(0, "Crop not Found");
        }
    }

    @Override
    public void updateCrop(CropDTO updateCropDTO) {
        Optional<CropEntity> tmpCrop = cropDao.findById(updateCropDTO.getCropCode());
        if (!tmpCrop.isPresent()) {
            throw new CropNotFound("Crop not Found");
        } else {
            CropEntity cropEntity = tmpCrop.get();
            cropEntity.setCategory(updateCropDTO.getCategory());
            cropEntity.setCropCommonName(updateCropDTO.getCropCommonName());
            cropEntity.setCropScientificName(updateCropDTO.getCropScientificName());
            cropEntity.setCropSeason(updateCropDTO.getCropSeason());
            cropEntity.setCropImage(updateCropDTO.getCropImage());

            // Save the updated entity
            cropDao.save(cropEntity);  // This line ensures the entity is saved to the database
        }
    }

    @Override
    public void deleteCrop(String cropCode) {
        Optional<CropEntity> findId = cropDao.findById(cropCode);
        if (!findId.isPresent()){
            throw new CropNotFound("Crop not Found");
        }else {
            cropDao.deleteById(cropCode);
        }
    }
}