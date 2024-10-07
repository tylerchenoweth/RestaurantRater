package com.example.restaurantrater.controllers;

import com.example.restaurantrater.entities.Reviewer;
import com.example.restaurantrater.services.ReviewerService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RegistrationController {

    @Autowired
    private ReviewerService reviewerService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("reviewer", new Reviewer());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("reviewer") Reviewer reviewer,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        if (reviewerService.findByUsername(reviewer.getUsername()) != null) {
            model.addAttribute("usernameError", "Username already exists");
            return "register";
        }
        reviewerService.registerReviewer(reviewer);
        return "redirect:/login";
    }
}
