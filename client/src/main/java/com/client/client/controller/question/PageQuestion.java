package com.client.client.controller.question;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageQuestion implements IQuestion {

    @PostMapping(value = "/products/qst/ref-date")
    private String getProductsRefDate(String ref, String date, Model model) {

        if (new File(FOLDER_QUESTION).exists()) {
            CreateQuestion.writeQuestionCodex001(new File(FOLDER_QUESTION), ref, date);
        }

        model.addAttribute("wait", true);
        model.addAttribute("qst", "ref-date");
        model.addAttribute("title", "Date de panne et référence");

        return "product/response";
    }

    @PostMapping(value = "/products/qst/ref-brand")
    private String getProductsBrandRef(String ref, String brand, Model model) {

        if (new File(FOLDER_QUESTION).exists()) {
            CreateQuestion.writeQuestionCodex002(new File(FOLDER_QUESTION), ref, brand.toLowerCase());
        }

        model.addAttribute("wait", true);
        model.addAttribute("qst", "ref-brand");
        model.addAttribute("title", "Marque et référence");

        return "product/response";
    }

    @GetMapping(value = "/product/qst/ref-date")
    private String productRefDate() {
        return "/product/question/ref-date";
    }

    @GetMapping(value = "/product/qst/ref-brand")
    private String productRefBrand() {
        return "/product/question/ref-brand";
    }
}
