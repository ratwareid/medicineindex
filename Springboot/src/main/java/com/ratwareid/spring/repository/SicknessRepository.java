package com.ratwareid.spring.repository;

import com.ratwareid.spring.model.MedicineModel;
import com.ratwareid.spring.model.SicknessModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SicknessRepository extends JpaRepository<SicknessModel,Integer> {

    @Query(value = "select * from sickness_model  where disease ilike  %:key% ", nativeQuery = true)
    List<SicknessModel> findAllIdentifiedDisease(String key);
}
