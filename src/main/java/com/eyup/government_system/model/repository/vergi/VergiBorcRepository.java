package com.eyup.government_system.model.repository.vergi;

import com.eyup.government_system.model.entity.vergi.VergiBorc;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VergiBorcRepository extends JpaRepository<VergiBorc, Long> {
    List<VergiBorc> findByTcKimlikNo(String tcKimlikNo);
}