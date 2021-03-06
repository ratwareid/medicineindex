package com.ratwareid.spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse extends GeneralResponse {

    @JsonProperty("id")
    private int id;
    @JsonProperty("username")
    private String UserName;
    @JsonProperty("password")
    private String Password;
    @JsonProperty("firstname")
    private String FirstName;
    @JsonProperty("lastname")
    private String LastName;
    @JsonProperty("token")
    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
