package com.example.aad_crop_management.service.impl;

import com.example.aad_crop_management.customObj.EquipmentResponse;
import com.example.aad_crop_management.customObj.impl.EquipmentErrorResponse;
import com.example.aad_crop_management.dao.EquipmentDao;
import com.example.aad_crop_management.dto.impl.EquipmentDTO;
import com.example.aad_crop_management.entity.EquipmentEntity;
import com.example.aad_crop_management.enums.Status;
import com.example.aad_crop_management.enums.Type;
import com.example.aad_crop_management.exception.DataPersistFailedException;
import com.example.aad_crop_management.exception.EquipmentNotFound;
import com.example.aad_crop_management.service.EquipmentService;
import com.example.aad_crop_management.utill.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentServiceIMPL implements EquipmentService {
    @Autowired
    private EquipmentDao equipmentDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveEquipment(EquipmentDTO equipmentDTO) {
        equipmentDTO.setEquipmentId(AppUtil.createEquipmentId());
        var equipmentEntity = mapping.convertToEquipmentEntity(equipmentDTO);
        var savedEquipment = equipmentDao.save(equipmentEntity);
        if (savedEquipment == null){
            throw new DataPersistFailedException("Cannot save equipment");
        }
    }

    @Override
    public List<EquipmentDTO> getAllEquipments() {
        List<EquipmentEntity> getAllEquipments = equipmentDao.findAll();
        return mapping.convertEquipmentToDTOList(getAllEquipments);
    }

    @Override
    public EquipmentResponse getSelectedEquipment(String equipmentId) {
        if (equipmentDao.existsById(equipmentId)) {
            EquipmentEntity equipmentEntityByEquipmentId = equipmentDao.getReferenceById(equipmentId);
            return mapping.convertToEquipmentDTO(equipmentEntityByEquipmentId);
        } else {
            return new EquipmentErrorResponse(0, "Equipment not Found");
        }
    }

    @Override
    public void updateEquipment(String equipmentId, EquipmentDTO incomeequipmentDTO) {
        Optional<EquipmentEntity> tmpEquipmentEntity = equipmentDao.findById(equipmentId);

        if (!tmpEquipmentEntity.isPresent()) {
            throw new EquipmentNotFound("Equipment not found");
        } else {
            EquipmentEntity equipmentEntity = tmpEquipmentEntity.get();

            equipmentEntity.setName(incomeequipmentDTO.getName());
            equipmentEntity.setType(Type.valueOf(incomeequipmentDTO.getType()));
            equipmentEntity.setStatus(Status.valueOf(incomeequipmentDTO.getStatus()));

            equipmentDao.save(equipmentEntity);
        }
    }

    @Override
    public void deleteEquipment(String equipmentId) {
        Optional<EquipmentEntity> findId = equipmentDao.findById(equipmentId);
        if (!findId.isPresent()){
            throw new EquipmentNotFound("Equipment not Found");
        }else {
            equipmentDao.deleteById(equipmentId);
        }
    }

}