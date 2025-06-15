package com.eyup.government_system.dto.sgk;


import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class SgkQueryRequest
{
    @NotBlank
    @Pattern(regexp="\\d{11}", message="TC Kimlik 11 haneli olmalı")
    private String tcKimlikNo;

    @Digits(integer = 11, fraction = 0, message = "Sadece rakam girilmeli")

    public String getTcKimlikNo() {
        return tcKimlikNo;
    }

    public void setTcKimlikNo(String tcKimlikNo) {
        this.tcKimlikNo = tcKimlikNo;
    }
}
