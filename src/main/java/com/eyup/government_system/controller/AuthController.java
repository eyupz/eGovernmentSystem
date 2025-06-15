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
public class AuthController
{

    private final AuthService authService;
    public AuthController(AuthService authService) { this.authService = authService; }

    @GetMapping("/login")
    public String showLogin(Model m) {
        m.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@Valid @ModelAttribute("loginRequest") LoginRequest req,
                          BindingResult br,
                          HttpSession session,
                          Model model) {
        if (br.hasErrors()) return "login";

        String tc = req.getTcKimlikNo();
        String cred = req.getCredential();

        // 1) Kullanıcının şifresi varsa -> şifreyle authenticate
        UserAccount user = authService.authenticateWithPassword(tc, cred).orElse(null);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/dashboard";
        }

        // 2) Şifre yoksa -> kodla authenticate
        user = authService.authenticateWithCode(tc, cred).orElse(null);
        if (user != null) {
            session.setAttribute("tempUser", user);
            return "redirect:/set-password";
        }

        model.addAttribute("error", "Geçersiz TC veya kod/şifre.");
        return "login";
    }

    @GetMapping("/set-password")
    public String showSetPassword(HttpSession session, Model m) {
        if (session.getAttribute("tempUser")==null) return "redirect:/login";
        m.addAttribute("setPwdReq", new SetPasswordRequest());
        return "set-password";
    }

    @PostMapping("/set-password")
    public String doSetPassword(@Valid @ModelAttribute("setPwdReq") SetPasswordRequest req,
                                BindingResult br,
                                HttpSession session,
                                Model model) {
        if (br.hasErrors()) return "set-password";
        if (!req.getNewPassword().equals(req.getConfirmPassword())) {
            model.addAttribute("error","Şifreler eşleşmiyor.");
            return "set-password";
        }
        UserAccount temp = (UserAccount)session.getAttribute("tempUser");
        authService.setPassword(temp, req.getNewPassword());
        session.removeAttribute("tempUser");
        session.setAttribute("user", temp);
        return "redirect:/dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
