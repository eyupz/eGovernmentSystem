package com.eyup.government_system.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_account")
public class UserAccount
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tc_kimlik_no", length = 11, unique = true, nullable = false)
    private String tcKimlikNo;

    @Column(name = "code", nullable = false)
    private String code; // Kullanıcının giriş yaparken kullandığı PTT kodu.

    @Column(name = "password")
    private String password;

    public UserAccount() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTcKimlikNo() {
        return tcKimlikNo;
    }

    public void setTcKimlikNo(String tcKimlikNo) {
        this.tcKimlikNo = tcKimlikNo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
