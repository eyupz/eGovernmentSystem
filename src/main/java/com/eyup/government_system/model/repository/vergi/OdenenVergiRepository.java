package com.eyup.government_system.model.repository.vergi;

import com.eyup.government_system.model.entity.vergi.OdenenVergi;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OdenenVergiRepository extends JpaRepository<OdenenVergi, Long> {
    List<OdenenVergi> findByTcKimlikNo(String tcKimlikNo);
}
