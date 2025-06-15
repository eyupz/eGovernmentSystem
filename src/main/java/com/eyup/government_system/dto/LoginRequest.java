package com.eyup.government_system.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class LoginRequest
{

    @NotBlank
    @Pattern(regexp="\\d{11}", message="TC Kimlik No 11 rakam olmalı")
    private String tcKimlikNo;

    @NotBlank
    private String credential;

    public String getTcKimlikNo() {
        return tcKimlikNo;
    }

    public void setTcKimlikNo(String tcKimlikNo) {
        this.tcKimlikNo = tcKimlikNo;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }
}
