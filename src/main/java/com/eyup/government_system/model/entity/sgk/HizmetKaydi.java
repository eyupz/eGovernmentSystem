package com.eyup.government_system.model.entity.sgk;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "hizmet_kaydi")
public class HizmetKaydi
{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tc_kimlik_no", length = 11, nullable = false)
    private String tcKimlikNo;

    @Column(name = "kurum_adi", nullable = false)
    private String kurumAdi;

    @Column(name = "baslangic_tarihi", nullable = false)
    private LocalDate baslangicTarihi;

    @Column(name = "bitis_tarihi", nullable = false)
    private LocalDate bitisTarihi;

    @Column(name = "prim_gunu", nullable = false)
    private int primGunu;

    public HizmetKaydi() {}

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

    public String getKurumAdi() {
        return kurumAdi;
    }

    public void setKurumAdi(String kurumAdi) {
        this.kurumAdi = kurumAdi;
    }

    public LocalDate getBaslangicTarihi() {
        return baslangicTarihi;
    }

    public void setBaslangicTarihi(LocalDate baslangicTarihi) {
        this.baslangicTarihi = baslangicTarihi;
    }

    public LocalDate getBitisTarihi() {
        return bitisTarihi;
    }

    public void setBitisTarihi(LocalDate bitisTarihi) {
        this.bitisTarihi = bitisTarihi;
    }

    public int getPrimGunu() {
        return primGunu;
    }

    public void setPrimGunu(int primGunu) {
        this.primGunu = primGunu;
    }
}
