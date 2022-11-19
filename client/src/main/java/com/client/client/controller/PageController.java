package com.client.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping(value = { "/", "/index" })
    private String redirectUser() {
        return "/home";
    }

    @RequestMapping(value = "/home")
    private String homePage() {
        return "home";
    }
}
