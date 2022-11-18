package com.client.client.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    private String hello(@RequestBody Products p) {
        System.out.println("****************!!!!!");
        System.out.println(p.toString());
        return "home";
    }

    @GetMapping(value = "/product/qst/ref-date")
    private String ProductRefDate() {
        return "/product/ref-date";
    }

}
