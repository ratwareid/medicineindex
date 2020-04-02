package com.ratwareid.spring.repository;

import com.ratwareid.spring.model.MedicineModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineRepository extends JpaRepository<MedicineModel,Integer> {

    List<MedicineModel> findAllByDiseaseContains(String key);
}
