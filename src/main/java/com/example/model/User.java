package com.example.model;
import com.fasterxml.jackson.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class User {

    @JsonProperty("id")
    private int id;

    @JsonProperty("registrationDate")
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss")
    private String registrationDate;

    @JsonProperty("email")
    private String email;

    @JsonProperty("country")
    private String country;

    @JsonIgnore
    private boolean isSent;

    public User() {
    }

    public User(int id, String registrationDate, String email, String country, boolean isSent) {
        this.id = id;
        this.registrationDate = registrationDate;
        this.email = email;
        this.country = country;
        this.isSent = isSent;
    }

    public int getId() {
        return id;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isSent() {
        return isSent;
    }
}
