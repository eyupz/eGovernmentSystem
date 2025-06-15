package com.eyup.government_system.model.service.sgk;

import com.eyup.government_system.model.entity.sgk.HizmetKaydi;
import com.eyup.government_system.model.entity.sgk.PrimBorc;
import com.eyup.government_system.model.entity.sgk.VatandasDurum;
import com.eyup.government_system.model.repository.sgk.HizmetKaydiRepository;
import com.eyup.government_system.model.repository.sgk.PrimBorcRepository;
import com.eyup.government_system.model.repository.sgk.VatandasDurumRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SgkService {

    private final HizmetKaydiRepository hizmetRepo;
    private final PrimBorcRepository borcRepo;
    private final VatandasDurumRepository durumRepo;

    public SgkService(HizmetKaydiRepository hizmetRepo,
                      PrimBorcRepository borcRepo,
                      VatandasDurumRepository durumRepo)
    {
        this.hizmetRepo = hizmetRepo;
        this.borcRepo = borcRepo;
        this.durumRepo = durumRepo;
    }

    public List<HizmetKaydi> getHizmetKayitlari(String tcKimlikNo)
    {
        return hizmetRepo.findByTcKimlikNo(tcKimlikNo);
    }

    public List<PrimBorc> getBorcListesi(String tcKimlikNo)
    {
        return borcRepo.findByTcKimlikNo(tcKimlikNo);
    }

    public Optional<VatandasDurum> getDurum(String tcKimlikNo)
    {
        return durumRepo.findByTcKimlikNo(tcKimlikNo);
    }
}
