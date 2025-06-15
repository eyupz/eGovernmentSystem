package com.eyup.government_system.model.service;

import com.eyup.government_system.model.entity.UserAccount;
import com.eyup.government_system.model.repository.UserAccountRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {

    private final UserAccountRepository repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthService(UserAccountRepository repo) {
        this.repo = repo;
    }

    // İlk giriş: kodu kontrol et
    public Optional<UserAccount> authenticateWithCode(String tc, String code) {
        return repo.findByTcKimlikNoAndCode(tc, code);
    }

    // Sonraki giriş: şifreyi kontrol et
    public Optional<UserAccount> authenticateWithPassword(String tc, String rawPassword) {
        Optional<UserAccount> user = repo.findByTcKimlikNo(tc);
        if (user.isPresent() && encoder.matches(rawPassword, user.get().getPassword())) {
            return user;  // Şifre doğruysa kullanıcıyı döndürüyoruz
        }
        return Optional.empty();  // Şifre uymazsa boş döndürüyoruz
    }

    // Şifre belirleme: kodu null, şifreyi hash’le
    public void setPassword(UserAccount user, String rawPassword) {
        if (user == null) {
            throw new IllegalArgumentException("Kullanıcı bulunamadı");
        }
        String hashed = encoder.encode(rawPassword);
        user.setPassword(hashed);
        user.setCode(null);
        repo.save(user);
    }
}
