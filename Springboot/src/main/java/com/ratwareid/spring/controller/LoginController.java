package com.ratwareid.spring.controller;

import com.ratwareid.spring.Helper.GeneralHelper;
import com.ratwareid.spring.dto.LogRegRequest;
import com.ratwareid.spring.dto.LoginResponse;
import com.ratwareid.spring.model.UserModel;
import com.ratwareid.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/api/login")
    public LoginResponse loginUser(
            @RequestBody LogRegRequest logindata){
        LoginResponse response = new LoginResponse();

        try{
            UserModel userModel = userRepository.findUserModelByUsernameEqualsAndPasswordEquals(logindata.getUserName(),logindata.getPassword());
            if (userModel == null) {
                throw new Exception("User / Password salah!");
            }
            response.setUserToken(GeneralHelper.generateRandomString(String.valueOf(userModel.getUserid())));
            response.setResponseCode(100);
            response.setResponseMessage("Login Successfully");
        }catch (Exception e){
            e.printStackTrace();
            response.setResponseCode(400);
            response.setResponseMessage(e.getLocalizedMessage());
        }
        return response;
    }

    @PostMapping(value = "/api/register")
    public LoginResponse registerUser(
            @RequestBody LogRegRequest data){
        LoginResponse response = new LoginResponse();

        try{
            UserModel userModel = new UserModel(data.getUserName(),data.getPassword(),data.getEmail(),data.getFirstName(),data.getLastName());
            userRepository.save(userModel);
            response.setResponseCode(100);
            response.setResponseMessage("Register Successfully");
        }catch (Exception e){
            e.printStackTrace();
            response.setResponseCode(400);
            response.setResponseMessage(e.getLocalizedMessage());
        }
        return response;
    }
}
