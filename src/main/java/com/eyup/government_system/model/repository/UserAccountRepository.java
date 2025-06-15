package com.eyup.government_system.model.repository;

import com.eyup.government_system.model.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long>
{
    Optional<UserAccount> findByTcKimlikNoAndCode(String tcKimlikNo, String code);

    Optional<UserAccount> findByTcKimlikNoAndPassword(String tcKimlikNo, String password);
}