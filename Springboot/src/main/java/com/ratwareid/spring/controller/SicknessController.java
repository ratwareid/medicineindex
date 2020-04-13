package com.ratwareid.spring.controller;

import com.ratwareid.spring.dto.GetMedicineResponse;
import com.ratwareid.spring.dto.GetSicknessResponse;
import com.ratwareid.spring.model.MedicineModel;
import com.ratwareid.spring.model.SicknessModel;
import com.ratwareid.spring.repository.MedicineRepository;
import com.ratwareid.spring.repository.SicknessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class SicknessController {

    @Autowired
    private SicknessRepository sicknessRepository;

    @RequestMapping(value = "/api/findsickness/{query}")
    public List<GetSicknessResponse> searchSickness(@PathVariable("query") String q){
        List<GetSicknessResponse> list = new ArrayList<>();
        if (q.equals("")) return null;

        String[] key = q.split(",");
        List<SicknessModel> collectdata = new ArrayList<>();

        //Ambil semua data
        for (int x=0;x<key.length;x++){
            List<SicknessModel> data = sicknessRepository.findAllIdentifiedDisease(key[x].toUpperCase().trim());
            collectdata.addAll(data);
        }

        //Remove duplicate data
        Set<SicknessModel> set = new HashSet<>(collectdata);
        collectdata.clear();
        collectdata.addAll(set);

        //Masukkan ke List Response
        for(Object a : collectdata){
            SicknessModel us = (SicknessModel) a;

            GetSicknessResponse rs = new GetSicknessResponse();
            rs.setSicknessid(us.getSicknessid());
            rs.setName(us.getName());
            rs.setDisease(us.getDisease());
            rs.setDescription(us.getDescription());
            rs.setAcurate(BigDecimal.ZERO);

            list.add(rs);
        }

        //Hitung persentase akurasi
        for (Object b : list){
            GetSicknessResponse medicine = (GetSicknessResponse) b;
            String[] diseasemed = medicine.getDisease().split(",");
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
        for (int e=0;e<list.size();e++){
            for (int f=0;f<list.size()-1;f++){
                GetSicknessResponse current = (GetSicknessResponse) list.get(f);
                GetSicknessResponse next = (GetSicknessResponse) list.get(f+1);
                if (current.getAcurate().compareTo(next.getAcurate()) < 0){
                    list.set(f,next);
                    list.set(f+1,current);
                }
            }
        }

        //TODO :: Sorting dari gejala terbanyak
        for (int g=0;g<list.size();g++){
            for (int h=0;h<list.size()-1;h++){
                GetSicknessResponse current = (GetSicknessResponse) list.get(h);
                GetSicknessResponse next = (GetSicknessResponse) list.get(h+1);
                String[] dcur = current.getDisease().split(",");
                String[] dnext = next.getDisease().split(",");
                if (current.getAcurate().compareTo(next.getAcurate()) == 0){
                    if (dcur.length < dnext.length){
                        list.set(h,next);
                        list.set(h+1,current);
                    }
                }
            }
        }

        //TODO ::Limit Top 5 Jika data lebih dari 5
        if (list.size() > 5) {
            List<GetSicknessResponse> newlist = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                GetSicknessResponse data = list.get(i);
                newlist.add(data);
            }
            list.clear();
            list.addAll(newlist);
        }
        return list;
    }
}
