package com.eyup.government_system.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SetPasswordRequest
{

    @NotBlank
    @Size(min = 6, message="Şifre en az 6 karakter olmalı")
    private String newPassword;

    @NotBlank
    private String confirmPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
