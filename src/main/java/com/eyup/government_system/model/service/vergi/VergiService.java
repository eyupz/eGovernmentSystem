package com.eyup.government_system.model.service.vergi;

import com.eyup.government_system.model.entity.vergi.Beyanname;
import com.eyup.government_system.model.entity.vergi.OdenenVergi;
import com.eyup.government_system.model.entity.vergi.VergiBorc;
import com.eyup.government_system.model.repository.vergi.BeyannameRepository;
import com.eyup.government_system.model.repository.vergi.OdenenVergiRepository;
import com.eyup.government_system.model.repository.vergi.VergiBorcRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VergiService
{

    private final VergiBorcRepository borcRepo;
    private final OdenenVergiRepository odenenRepo;
    private final BeyannameRepository beyanRepo;

    public VergiService(VergiBorcRepository borcRepo,
                        OdenenVergiRepository odenenRepo,
                        BeyannameRepository beyanRepo)
    {
        this.borcRepo = borcRepo;
        this.odenenRepo = odenenRepo;
        this.beyanRepo = beyanRepo;
    }

    public List<VergiBorc> getVergiBorcListesi(String tcKimlikNo)
    {
        return borcRepo.findByTcKimlikNo(tcKimlikNo);
    }

    public List<OdenenVergi> getOdenenVergiler(String tcKimlikNo)
    {
        return odenenRepo.findByTcKimlikNo(tcKimlikNo);
    }

    public List<Beyanname> getBeyannameListesi(String tcKimlikNo)
    {
        return beyanRepo.findByTcKimlikNo(tcKimlikNo);
    }
}
