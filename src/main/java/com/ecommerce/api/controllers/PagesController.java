package com.ecommerce.api.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class PagesController {


    @GetMapping(path="/home")
    public ModelAndView getHome() {
        String message1 = "Bienvenue sur notre site d'e-commerce!";
        String message2 = "Voici la liste de nos produits les plus populaires!";
        ModelAndView mav = new ModelAndView("home");
        mav.addObject("message1", message1);
        mav.addObject("message2", message2);
        return mav;
    }

    @GetMapping(path="/products")
    public ModelAndView getProducts() {
        String message = "Voici la liste de nos produits.";
        ModelAndView mav = new ModelAndView("products");
        mav.addObject("message", message);
        return mav;
    }

}
