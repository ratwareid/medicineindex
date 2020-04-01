package com.ratwareid.spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetMedicineResponse extends GeneralResponse {

    @JsonProperty("medicineid")
    private int medicineid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("disease")
    private String disease;

    @JsonProperty("description")
    private String description;

    @JsonProperty("rulesofuse")
    private String rulesofuse;

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
