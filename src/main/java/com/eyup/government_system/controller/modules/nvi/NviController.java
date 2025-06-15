package com.eyup.government_system.controller.modules.nvi;

import com.eyup.government_system.controller.BaseController;
import com.eyup.government_system.model.entity.nvi.Vatandas;
import com.eyup.government_system.model.service.nvi.NviService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/query/nvi")
public class NviController extends BaseController {

    private final NviService nviService;

    public NviController(NviService nviService) {
        this.nviService = nviService;
    }

    @GetMapping
    public String showForm(HttpSession session, RedirectAttributes ra) {
        String redirect = checkAuth(session, ra);
        if (redirect != null) return redirect;
        return "nvi/form";
    }

    @PostMapping
    public String doQuery(@RequestParam String tcKimlikNo,
                          Model model,
                          HttpSession session,
                          RedirectAttributes ra) {
        String redirect = checkAuth(session, ra);
        if (redirect != null) return redirect;

        Optional<Vatandas> vatandasOpt = nviService.getVatandasByTc(tcKimlikNo);
        if (vatandasOpt.isPresent()) {
            model.addAttribute("vatandas", vatandasOpt.get());
        } else {
            model.addAttribute("error", "Bu TC Kimlik No’ya ait kayıt bulunamadı.");
        }
        return "nvi/sonuc";
    }
}
