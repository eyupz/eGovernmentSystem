package com.eyup.government_system.model.entity.vergi;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "odenen_vergi")
public class OdenenVergi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tc_kimlik_no", length = 11, nullable = false)
    private String tcKimlikNo;

    @Column(name = "tarih", nullable = false)
    private LocalDate tarih;

    @Column(name = "tutar", nullable = false)
    private double tutar;

    public OdenenVergi() {}

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

    public LocalDate getTarih() {
        return tarih;
    }

    public void setTarih(LocalDate tarih) {
        this.tarih = tarih;
    }

    public double getTutar() {
        return tutar;
    }

    public void setTutar(double tutar) {
        this.tutar = tutar;
    }
}
