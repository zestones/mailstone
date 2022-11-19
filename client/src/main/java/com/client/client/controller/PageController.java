package com.client.client.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.client.client.model.Products;

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

    @PostMapping(value = "/ok", headers = {
            "Content-type=application/xml" }, consumes = MediaType.APPLICATION_XML_VALUE)
    private String hello(@RequestBody List<Products> p, Model model) {

        System.out.println("****************!!!!!");
        System.out.println(p.toString());
        model.addAttribute("products", p);

        return "redirect:/home";
    }
}
