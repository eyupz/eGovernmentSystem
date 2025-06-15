package com.eyup.government_system.controller;

import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public abstract class BaseController
{
    protected boolean isAuthenticated(HttpSession session)
    {
        return session.getAttribute("user") != null;
    }

    protected String checkAuth(HttpSession session, RedirectAttributes ra)
    {
        if (!isAuthenticated(session))
        {
            ra.addFlashAttribute("error", "Lütfen önce giriş yapın.");
            return "redirect:/login";
        }
        return null;
    }
}