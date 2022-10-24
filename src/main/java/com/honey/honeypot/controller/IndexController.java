package com.honey.honeypot.controller;

import com.honey.honeypot.service.GeoLocationService;
import com.honey.honeypot.service.StartupService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@Controller
@RequiredArgsConstructor
public class IndexController {

    @Value("${app.dummy-username}")
    private String dummyUsername;

    private final GeoLocationService geoLocationService;
    private final StartupService startupService;

    @GetMapping("/nLTUGsNx")
    public String viewLink(HttpServletRequest request, Model model) {
        model.addAttribute("dummyUserName", dummyUsername);
        model.addAttribute("createdAt", startupService.getStartupEntity().getStartedAt()
                .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.US)));
        geoLocationService.insertNew(request.getRemoteAddr(), null, null, null);
        return "index";
    }

    @GetMapping("/")
    public String viewHomePage(HttpServletRequest request, Model model) {
        return viewLink(request, model);
    }
}
