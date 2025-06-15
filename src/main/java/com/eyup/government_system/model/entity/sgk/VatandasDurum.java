package com.eyup.government_system.model.entity.sgk;

import javax.persistence.*;

@Entity
@Table(name = "vatandas_durum")
public class VatandasDurum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tc_kimlik_no", length = 11, nullable = false)
    private String tcKimlikNo;

    @Column(name = "durum", nullable = false)
    private String durum;

    public VatandasDurum() {}

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

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }
}
