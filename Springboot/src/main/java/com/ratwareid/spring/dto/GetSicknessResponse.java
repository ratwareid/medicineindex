package com.ratwareid.spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class GetSicknessResponse extends GeneralResponse {

    @JsonProperty("sicknessid")
    private int sicknessid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("disease")
    private String disease;

    @JsonProperty("description")
    private String description;

    @JsonProperty("acurate")
    private BigDecimal acurate;

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

    public BigDecimal getAcurate() {
        return acurate;
    }

    public void setAcurate(BigDecimal acurate) {
        this.acurate = acurate;
    }
}
