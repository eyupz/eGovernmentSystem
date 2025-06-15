package com.eyup.government_system.model.repository.nvi;

import com.eyup.government_system.model.entity.nvi.Vatandas;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface VatandasRepository extends JpaRepository<Vatandas, Long> {
    Optional<Vatandas> findByTcKimlikNo(String tcKimlikNo);
}