package com.ratwareid.spring.dto;

public class LoginResponse extends GeneralResponse {
    private String userToken;

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
