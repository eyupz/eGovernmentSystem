package com.eyup.government_system.model.service.nvi;

import com.eyup.government_system.model.entity.nvi.Vatandas;
import com.eyup.government_system.model.repository.nvi.VatandasRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class NviService
{

    private final VatandasRepository vatandasRepo;

    public NviService(VatandasRepository vatandasRepo)
    {
        this.vatandasRepo = vatandasRepo;
    }

    public Optional<Vatandas> getVatandasByTc(String tcKimlikNo)
    {
        return vatandasRepo.findByTcKimlikNo(tcKimlikNo);
    }
}
