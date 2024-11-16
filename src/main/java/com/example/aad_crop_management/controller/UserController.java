package com.example.aad_crop_management.controller;

import com.example.aad_crop_management.customObj.UserResponse;
import com.example.aad_crop_management.dto.impl.UserDTO;
import com.example.aad_crop_management.exception.DataPersistFailedException;
import com.example.aad_crop_management.exception.UserNotFound;
import com.example.aad_crop_management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController  {
    @Autowired
    private final UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveUser(@RequestBody UserDTO user){
        if(user == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            try{
                userService.saveUser(user);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }catch (DataPersistFailedException e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping(value = "/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse getSelectedUser(@PathVariable("email") String email){
        return userService.getSelectedUser(email);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateUser(@PathVariable("email") String email, @RequestBody UserDTO user){
        try{
            if (user == null && (email == null || user.equals(""))){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            userService.updateUser(email,user);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (UserNotFound e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable("email") String email){
        try{
            userService.deleteUser(email);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (UserNotFound e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}