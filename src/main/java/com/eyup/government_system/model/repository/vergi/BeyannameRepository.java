package com.eyup.government_system.model.repository.vergi;

import com.eyup.government_system.model.entity.vergi.Beyanname;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BeyannameRepository extends JpaRepository<Beyanname, Long> {
    List<Beyanname> findByTcKimlikNo(String tcKimlikNo);
}