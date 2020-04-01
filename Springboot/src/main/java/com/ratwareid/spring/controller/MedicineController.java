package com.ratwareid.spring.controller;

import com.ratwareid.spring.dto.GetMedicineResponse;
import com.ratwareid.spring.model.MedicineModel;
import com.ratwareid.spring.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MedicineController {

    @Autowired
    private MedicineRepository medicineRepository;

    @GetMapping(value = "/api/medicine")
    public List<GetMedicineResponse> getAllMedicine(){
        List<GetMedicineResponse> list = new ArrayList<>();

        try{
            List<MedicineModel> listdata = medicineRepository.findAll();
            for (Object x : listdata){

                MedicineModel us = (MedicineModel) x;
                GetMedicineResponse rs = new GetMedicineResponse();
                rs.setMedicineid(us.getMedicineid());
                rs.setName(us.getName());
                rs.setDisease(us.getDisease());
                rs.setDescription(us.getDescription());
                rs.setRulesofuse(us.getRulesofuse());
                list.add(rs);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

}
