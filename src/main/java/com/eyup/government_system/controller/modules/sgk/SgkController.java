package com.eyup.government_system.controller.modules.sgk;

import com.eyup.government_system.controller.BaseController;
import com.eyup.government_system.dto.sgk.SgkQueryRequest;
import com.eyup.government_system.model.entity.sgk.HizmetKaydi;
import com.eyup.government_system.model.entity.sgk.PrimBorc;
import com.eyup.government_system.model.entity.sgk.VatandasDurum;
import com.eyup.government_system.model.service.sgk.SgkService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Validated
@Controller
@RequestMapping("/query/sgk")
public class SgkController extends BaseController {

    private final SgkService sgkService;

    public SgkController(SgkService sgkService) {
        this.sgkService = sgkService;
    }

    @GetMapping
    public String showForm(HttpSession session,
                           RedirectAttributes ra,
                           Model model) {

        String redirect = checkAuth(session, ra);
        if (redirect != null) {
            return redirect;
        }

        model.addAttribute("sgkQueryRequest", new SgkQueryRequest());
        return "sgk/form";
    }

    @PostMapping
    public String doQuery(@Valid @ModelAttribute("sgkQueryRequest") SgkQueryRequest req,
                          BindingResult br,
                          HttpSession session,
                          RedirectAttributes ra,
                          Model model) {

        String redirect = checkAuth(session, ra);
        if (redirect != null) {
            return redirect;
        }

        if (br.hasErrors()) {
            return "sgk/form";
        }

        String tc = req.getTcKimlikNo();
        List<HizmetKaydi> hizmetler = sgkService.getHizmetKayitlari(tc);
        List<PrimBorc> borclar    = sgkService.getBorcListesi(tc);
        VatandasDurum durum        = sgkService.getDurum(tc).orElse(null);

        model.addAttribute("hizmetler", hizmetler);
        model.addAttribute("borclar", borclar);
        model.addAttribute("durum", durum);

        return "sgk/sonuc";
    }
}