package com.ratwareid.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class MedicineModel {

    @Id
    @GeneratedValue
    private int medicineid;
    private String name,disease,description,rulesofuse;

    public MedicineModel(String disease,String name,String description,String rulesofuse){
        this.name = name;
        this.disease = disease;
        this.description = description;
        this.rulesofuse = rulesofuse;
    }

    public int getMedicineid() {
        return medicineid;
    }

    public void setMedicineid(int medicineid) {
        this.medicineid = medicineid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRulesofuse() {
        return rulesofuse;
    }

    public void setRulesofuse(String rulesofuse) {
        this.rulesofuse = rulesofuse;
    }
}
