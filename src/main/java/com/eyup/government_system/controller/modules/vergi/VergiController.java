package com.eyup.government_system.controller.modules.vergi;

import com.eyup.government_system.controller.BaseController;
import com.eyup.government_system.model.entity.vergi.Beyanname;
import com.eyup.government_system.model.entity.vergi.OdenenVergi;
import com.eyup.government_system.model.entity.vergi.VergiBorc;
import com.eyup.government_system.model.service.vergi.VergiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/query/vergi")
public class VergiController extends BaseController {

    private final VergiService vergiService;

    public VergiController(VergiService vergiService) {
        this.vergiService = vergiService;
    }

    @GetMapping
    public String showForm(HttpSession session, RedirectAttributes ra) {
        String redirect = checkAuth(session, ra);
        if (redirect != null) return redirect;
        return "vergi/form";
    }

    @PostMapping
    public String doQuery(@RequestParam String tcKimlikNo,
                          Model model,
                          HttpSession session,
                          RedirectAttributes ra) {
        String redirect = checkAuth(session, ra);
        if (redirect != null) return redirect;

        List<VergiBorc> borclar = vergiService.getVergiBorcListesi(tcKimlikNo);
        List<OdenenVergi> odenenVergiler = vergiService.getOdenenVergiler(tcKimlikNo);
        List<Beyanname> beyanlar = vergiService.getBeyannameListesi(tcKimlikNo);

        model.addAttribute("borclar", borclar);
        model.addAttribute("odenenVergiler", odenenVergiler);
        model.addAttribute("beyanlar", beyanlar);

        return "vergi/sonuc";
    }
}
