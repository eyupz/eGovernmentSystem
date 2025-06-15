package com.eyup.government_system.controller;

import com.eyup.government_system.dto.LoginRequest;
import com.eyup.government_system.dto.SetPasswordRequest;
import com.eyup.government_system.model.entity.UserAccount;
import com.eyup.government_system.model.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class AuthController {

    private final AuthService authService;

    // Constructor injection of AuthService
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Giriş sayfası: /login (GET)
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        // Login formuna boş bir LoginRequest ekleyerek başlatıyoruz
        model.addAttribute("loginRequest", new LoginRequest());
        return "login"; // login.html
    }

    // Giriş işlemi: /login (POST)
    @PostMapping("/login")
    public String doLogin(@Valid @ModelAttribute("loginRequest") LoginRequest req,
                          BindingResult br,
                          HttpSession session,
                          Model model) {

        // Eğer validasyon hatası varsa, login sayfasına geri dön
        if (br.hasErrors()) {
            return "login";
        }

        String tc = req.getTcKimlikNo();
        String cred = req.getCredential();  // Kod veya Şifre

        // 1. Kullanıcıyı TC ve Kod ile kontrol et (ilk giriş)
        UserAccount user = authService.authenticateWithCode(tc, cred).orElse(null);

        if (user != null) {
            // Geçici kullanıcı bilgilerini session'a ekliyoruz
            session.setAttribute("tempUser", user);
            return "redirect:/set-password";  // Şifre belirleme sayfasına yönlendir
        }

        // 2. Kullanıcıyı TC ve Şifre ile kontrol et (sonraki girişler)
        user = authService.authenticateWithPassword(tc, cred).orElse(null);
        if (user != null) {
            // Şifreyle başarılı giriş yaptıysa, kullanıcıyı session'a ekliyoruz
            session.setAttribute("user", user);
            return "redirect:/dashboard";  // Dashboard'a yönlendir
        }

        // Eğer giriş başarısızsa, hata mesajı göster
        model.addAttribute("error", "Geçersiz TC Kimlik No veya Kod/Şifre.");
        return "login";  // Geri login sayfasına dön
    }

    // Şifre belirleme sayfası: /set-password (GET)
    @GetMapping("/set-password")
    public String showSetPasswordPage(HttpSession session, Model model) {
        // Eğer geçici kullanıcı yoksa (ilk giriş yapılmamışsa), login sayfasına yönlendir
        if (session.getAttribute("tempUser") == null) {
            return "redirect:/login";
        }

        // Şifre belirleme formu için SetPasswordRequest nesnesi ekleyelim
        model.addAttribute("setPwdReq", new SetPasswordRequest());
        return "set-password"; // set-password.html
    }

    // Şifre belirleme işlemi: /set-password (POST)
    @PostMapping("/set-password")
    public String doSetPassword(@Valid @ModelAttribute("setPwdReq") SetPasswordRequest req,
                                BindingResult br,
                                HttpSession session,
                                Model model) {

        // Eğer validasyon hatası varsa, formu tekrar göster
        if (br.hasErrors()) {
            return "set-password";
        }

        // Şifreler eşleşiyor mu kontrol et
        if (!req.getNewPassword().equals(req.getConfirmPassword())) {
            model.addAttribute("error", "Şifreler eşleşmiyor.");
            return "set-password"; // Eşleşmiyorsa formu tekrar göster
        }

        // Geçici kullanıcıyı al
        UserAccount tempUser = (UserAccount) session.getAttribute("tempUser");

        // Şifreyi set et ve kodu null yap
        authService.setPassword(tempUser, req.getNewPassword());

        // Geçici kullanıcıyı session'dan kaldır, kalıcı kullanıcıyı ekle
        session.removeAttribute("tempUser");
        session.setAttribute("user", tempUser);

        return "redirect:/dashboard"; // Şifre belirlendikten sonra dashboard'a yönlendir
    }

    // Çıkış işlemi: /logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Tüm oturum bilgilerini temizle
        return "redirect:/login"; // Login sayfasına yönlendir
    }
}
