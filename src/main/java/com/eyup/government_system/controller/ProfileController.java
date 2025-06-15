package com.eyup.government_system.controller;

import com.eyup.government_system.dto.SetPasswordRequest;
import com.eyup.government_system.model.entity.UserAccount;
import com.eyup.government_system.model.entity.nvi.Vatandas;
import com.eyup.government_system.model.service.AuthService;
import com.eyup.government_system.model.service.nvi.NviService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/profile")
public class ProfileController extends BaseController {

    private final NviService nviService;
    private final AuthService authService;

    public ProfileController(NviService nviService, AuthService authService) {
        this.nviService = nviService;
        this.authService = authService;
    }

    @GetMapping
    public String showProfile(HttpSession session, Model m) {
        UserAccount user = (UserAccount)session.getAttribute("user");
        if (user==null) return "redirect:/login";
        Vatandas v = nviService.getVatandasByTc(user.getTcKimlikNo()).orElse(null);
        m.addAttribute("vatandas", v);
        m.addAttribute("setPwdReq", new SetPasswordRequest());
        return "profile";
    }

    @PostMapping("/change-password")
    public String changePassword(@Valid @ModelAttribute("setPwdReq") SetPasswordRequest req,
                                 BindingResult br,
                                 HttpSession session,
                                 Model m) {
        UserAccount user = (UserAccount)session.getAttribute("user");
        if (user==null) return "redirect:/login";
        if (br.hasErrors()) {
            m.addAttribute("vatandas", nviService.getVatandasByTc(user.getTcKimlikNo()).orElse(null));
            return "profile";
        }
        if (!req.getNewPassword().equals(req.getConfirmPassword())) {
            m.addAttribute("error","Şifreler eşleşmiyor.");
            m.addAttribute("vatandas", nviService.getVatandasByTc(user.getTcKimlikNo()).orElse(null));
            return "profile";
        }
        authService.setPassword(user, req.getNewPassword());
        m.addAttribute("message","Şifreniz güncellendi.");
        m.addAttribute("vatandas", nviService.getVatandasByTc(user.getTcKimlikNo()).orElse(null));
        return "profile";
    }
}
