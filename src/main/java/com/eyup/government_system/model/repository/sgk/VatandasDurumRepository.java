package com.eyup.government_system.model.repository.sgk;

import com.eyup.government_system.model.entity.sgk.VatandasDurum;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface VatandasDurumRepository extends JpaRepository<VatandasDurum, Long> {
    Optional<VatandasDurum> findByTcKimlikNo(String tcKimlikNo);
}
