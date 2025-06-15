package com.eyup.government_system.model.entity.nvi;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vatandas_nvi")
public class Vatandas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tc_kimlik_no", length = 11, unique = true, nullable = false)
    private String tcKimlikNo;

    @Column(name = "ad", nullable = false)
    private String ad;

    @Column(name = "soyad", nullable = false)
    private String soyad;

    @Column(name = "dogum_tarihi", nullable = false)
    private LocalDate dogumTarihi;

    @Column(name = "adres", length = 512)
    private String adres;

    public Vatandas() {}

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

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public LocalDate getDogumTarihi() {
        return dogumTarihi;
    }

    public void setDogumTarihi(LocalDate dogumTarihi) {
        this.dogumTarihi = dogumTarihi;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }
}
