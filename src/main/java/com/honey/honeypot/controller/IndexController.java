package com.honey.honeypot.controller;

import com.honey.honeypot.service.GeoLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class IndexController {

    @Value("${app.dummy-username}")
    private String dummyUsername;

    private final GeoLocationService geoLocationService;

    @GetMapping("/nLTUGsNx")
    public String viewLink(HttpServletRequest request, Model model) {
        model.addAttribute("dummyUserName", dummyUsername);
        geoLocationService.insertNew(request.getRemoteAddr(), null, null);
        return "index";
    }

    @GetMapping("/")
    public String viewHomePage(HttpServletRequest request, Model model) {
        return viewLink(request, model);
    }
}
