package com.eyup.government_system.model.repository.sgk;

import com.eyup.government_system.model.entity.sgk.PrimBorc;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface PrimBorcRepository extends JpaRepository<PrimBorc, Long> {
    List<PrimBorc> findByTcKimlikNo(String tcKimlikNo);
}
