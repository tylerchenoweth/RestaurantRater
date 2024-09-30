package com.example.restaurantrater.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("name", "John Doe");  // Add dynamic data to the model
        return "home";  // This maps to the "home.html" template in src/main/resources/templates
    }
}
