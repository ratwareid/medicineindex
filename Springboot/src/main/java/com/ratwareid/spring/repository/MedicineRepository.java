package com.ratwareid.spring.repository;

import com.ratwareid.spring.model.MedicineModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface MedicineRepository extends JpaRepository<MedicineModel,Integer> {

    List<MedicineModel> findAllByDiseaseContains(String key);

    @Query(value = "select * from medicine_model  where disease ilike  %:key% ", nativeQuery = true)
    List<MedicineModel> findAllIdentifiedDisease(String key);
}
