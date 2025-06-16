package com.eyup.government_system.model.repository;

import com.eyup.government_system.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    // TC Kimlik No'ya göre kullanıcıyı bulur
    Optional<UserAccount> findByTcKimlikNo(String tcKimlikNo);

    // TC Kimlik No ve kodu ile kullanıcıyı bulur (ilk giriş için)
    Optional<UserAccount> findByTcKimlikNoAndCode(String tcKimlikNo, String code);

    // TC Kimlik No ve şifre ile kullanıcıyı bulur (sonraki giriş için)
    Optional<UserAccount> findByTcKimlikNoAndPassword(String tcKimlikNo, String password);
}
