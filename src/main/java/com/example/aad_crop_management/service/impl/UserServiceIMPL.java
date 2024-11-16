package com.example.aad_crop_management.service.impl;

import com.example.aad_crop_management.customObj.UserResponse;
import com.example.aad_crop_management.customObj.impl.UserErrorResponse;
import com.example.aad_crop_management.dao.UserDao;
import com.example.aad_crop_management.dto.impl.UserDTO;
import com.example.aad_crop_management.entity.UserEntity;
import com.example.aad_crop_management.enums.Role;
import com.example.aad_crop_management.exception.DataPersistFailedException;
import com.example.aad_crop_management.exception.UserNotFound;
import com.example.aad_crop_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceIMPL implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveUser(UserDTO userDTO) {
        var userEntity = mapping.convertToUserEntity(userDTO);
        var savedUser = userDao.save(userEntity);
        if (savedUser == null){
            throw new DataPersistFailedException("Cannot Save User");
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> getAllUsers = userDao.findAll();
        return mapping.convertUserToDTOList(getAllUsers);
    }

    @Override
    public UserResponse getSelectedUser(String email) {
        if (userDao.existsById(email)) {
            UserEntity userEntityByEmail = userDao.getReferenceById(email);
            return mapping.convertToUserDTO(userEntityByEmail);
        } else {
            return new UserErrorResponse(0, "User not Found");
        }
    }

    @Override
    public void updateUser(String email, UserDTO incomeUserDTO) {
        UserEntity existingUser = userDao.findById(email)
                .orElseThrow(() -> new UserNotFound("User not found with email: " + email));

        UserEntity updatedUser = new UserEntity();
        updatedUser.setEmail(incomeUserDTO.getEmail() != null ? incomeUserDTO.getEmail() : existingUser.getEmail());
        updatedUser.setPassword(incomeUserDTO.getPassword() != null ? incomeUserDTO.getPassword() : existingUser.getPassword());

        if (incomeUserDTO.getRole() != null) {
            updatedUser.setRole(Role.valueOf(String.valueOf(incomeUserDTO.getRole())));
        } else {
            updatedUser.setRole(existingUser.getRole());
        }

        userDao.delete(existingUser);

        userDao.save(updatedUser);
    }

    @Override
    public void deleteUser(String email) {
        Optional<UserEntity> findId = userDao.findById(email);
        if (!findId.isPresent()){
            throw new UserNotFound("User not Found");
        }else {
            userDao.deleteById(email);
        }
    }
}