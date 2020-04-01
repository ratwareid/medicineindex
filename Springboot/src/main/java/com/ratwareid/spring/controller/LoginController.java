package com.ratwareid.spring.controller;

import com.ratwareid.spring.Helper.GeneralHelper;
import com.ratwareid.spring.dto.GeneralResponse;
import com.ratwareid.spring.dto.LoginRequest;
import com.ratwareid.spring.dto.LoginResponse;
import com.ratwareid.spring.dto.RegisterRequest;
import com.ratwareid.spring.model.UserModel;
import com.ratwareid.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/api/login")
    public LoginResponse loginUser(
            @RequestBody LoginRequest logindata){

        LoginResponse response = new LoginResponse();
        try{
            UserModel userModel = userRepository.findUserModelByUsernameEqualsAndPasswordEquals(logindata.getUserName(),logindata.getPassword());

            if (userModel == null) {
                throw new Exception("User / Password salah!");
            }
            response.setToken(GeneralHelper.fakeToken);
            response.setPassword(userModel.getPassword());
            response.setId(userModel.getUserid());
            response.setFirstName(userModel.getFirstname());
            response.setLastName(userModel.getLastname());
            response.setResponseCode(100);
            response.setResponseMessage("Berhasil Login");
        }catch (Exception e){
            e.printStackTrace();
            response.setResponseCode(400);
            response.setResponseMessage(e.getMessage());
        }
        return response;
    }

    @PostMapping(value = "/api/register")
    public GeneralResponse registerUser(
            @RequestBody RegisterRequest data){
        GeneralResponse response = new GeneralResponse();

        try{
            UserModel userModel = new UserModel(data.getUserName(),data.getPassword(),data.getFirstName(),data.getLastName());
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

    @GetMapping(value = "/api/users")
    public List<LoginResponse> getAllUsers(){
        List<LoginResponse> list = new ArrayList<>();

        try{
            List<UserModel> listdata = userRepository.findAll();
            for (Object x : listdata){
                UserModel us = (UserModel) x;
                LoginResponse rs = new LoginResponse();
                rs.setId(us.getUserid());
                rs.setFirstName(us.getFirstname());
                rs.setLastName(us.getLastname());
                rs.setPassword(us.getPassword());
                rs.setToken(GeneralHelper.fakeToken);
                list.add(rs);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
