package com.eyup.government_system.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DashboardController extends BaseController {

    /**
     * /dashboard GET isteği: Oturum varsa dashboard, yoksa /login’a redirect.
     */
    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, RedirectAttributes ra, Model model) {
        String redirect = checkAuth(session, ra);
        if (redirect != null) {
            return redirect;
        }
        // Oturum açan kullanıcının TC’sini Thymeleaf’te gösterebiliriz:
        model.addAttribute("tcKimlikNo", ((com.eyup.government_system.model.UserAccount) session.getAttribute("user")).getTcKimlikNo());
        return "dashboard";
    }
}
