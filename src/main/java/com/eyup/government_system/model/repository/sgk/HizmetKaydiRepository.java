package com.eyup.government_system.model.repository.sgk;

import com.eyup.government_system.model.entity.sgk.HizmetKaydi;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HizmetKaydiRepository extends JpaRepository<HizmetKaydi, Long> {
    List<HizmetKaydi> findByTcKimlikNo(String tcKimlikNo);
}
