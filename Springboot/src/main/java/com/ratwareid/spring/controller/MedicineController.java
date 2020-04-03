package com.ratwareid.spring.controller;

import com.ratwareid.spring.dto.GeneralResponse;
import com.ratwareid.spring.dto.GetMedicineResponse;
import com.ratwareid.spring.dto.MedicineRequest;
import com.ratwareid.spring.model.MedicineModel;
import com.ratwareid.spring.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

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

    @PostMapping(value = "/api/medicine")
    public GeneralResponse insertData(
            @RequestBody MedicineRequest data){
        GeneralResponse response = new GeneralResponse();

        try{
            MedicineModel medicine = new MedicineModel(data.getDisease().toUpperCase(),data.getName(),data.getDescription(),data.getRulesofuse());
            medicineRepository.save(medicine);
            response.setResponseCode(100);
            response.setResponseMessage("Register Successfully");
        }catch (Exception e){
            e.printStackTrace();
            response.setResponseCode(400);
            response.setResponseMessage(e.getLocalizedMessage());
        }
        return response;
    }

    @RequestMapping(value = "/api/identified/{query}")
    public List<GetMedicineResponse> searchData(@PathVariable("query") String q){
        List<GetMedicineResponse> list = new ArrayList<>();
        if (q.equals("")) return getAllMedicine();

        String[] key = q.trim().split(",");
        List<MedicineModel> collectdata = new ArrayList<>();

        //Ambil semua data
        for (int x=0;x<key.length;x++){
            List<MedicineModel> data = medicineRepository.findAllIdentifiedDisease(key[x].toUpperCase());
            collectdata.addAll(data);
        }

        //Remove duplicate data
        Set<MedicineModel> set = new HashSet<>(collectdata);
        collectdata.clear();
        collectdata.addAll(set);

        //Masukkan ke List Response
        for(Object a : collectdata){
            MedicineModel us = (MedicineModel) a;

            GetMedicineResponse rs = new GetMedicineResponse();
            rs.setMedicineid(us.getMedicineid());
            rs.setName(us.getName());
            rs.setDisease(us.getDisease());
            rs.setDescription(us.getDescription());
            rs.setRulesofuse(us.getRulesofuse());
            rs.setAcurate(BigDecimal.ZERO);

            list.add(rs);
        }

        //Hitung persentase akurasi
        for (Object b : list){
            GetMedicineResponse medicine = (GetMedicineResponse) b;
            String[] diseasemed = medicine.getDisease().trim().split(",");
            BigDecimal totaldisease = new BigDecimal(diseasemed.length);
            int totalpass = 0;

            for (int c=0;c<diseasemed.length;c++){
                for (int y=0;y<key.length;y++) {
                    if (diseasemed[c].trim().equalsIgnoreCase(key[y].trim())){
                        totalpass++;
                    }
                }
            }
            BigDecimal tp = new BigDecimal(totalpass);
            BigDecimal acurate = tp.divide(totaldisease,2, RoundingMode.HALF_EVEN).multiply(new BigDecimal(100));
            medicine.setAcurate(acurate);
        }

        //TODO :: Sorting dari acurasi terbesar

        return list;
    }

}
