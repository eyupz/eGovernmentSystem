package com.eyup.government_system.model.entity.vergi;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "beyanname")
public class Beyanname {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tc_kimlik_no", length = 11, nullable = false)
    private String tcKimlikNo;

    @Column(name = "beyan_tarihi", nullable = false)
    private LocalDate beyanTarihi;

    @Column(name = "beyan_turu", nullable = false)
    private String beyanTuru;

    public Beyanname() {}

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

    public LocalDate getBeyanTarihi() {
        return beyanTarihi;
    }

    public void setBeyanTarihi(LocalDate beyanTarihi) {
        this.beyanTarihi = beyanTarihi;
    }

    public String getBeyanTuru() {
        return beyanTuru;
    }

    public void setBeyanTuru(String beyanTuru) {
        this.beyanTuru = beyanTuru;
    }
}