package com.ratwareid.spring.repository;

import com.ratwareid.spring.model.MedicineModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<MedicineModel,Integer> {
}
