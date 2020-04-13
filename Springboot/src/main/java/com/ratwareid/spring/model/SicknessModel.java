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
public class SicknessModel {

    @Id
    @GeneratedValue
    private int sicknessid;
    private String name,disease,description;

    public SicknessModel(String disease, String name, String description){
        this.name = name;
        this.disease = disease;
        this.description = description;
    }

    public int getSicknessid() {
        return sicknessid;
    }

    public void setSicknessid(int sicknessid) {
        this.sicknessid = sicknessid;
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

}
