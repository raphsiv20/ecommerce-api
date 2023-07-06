package com.ecommerce.api.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurityController {



    @GetMapping(path="/log")
    public String getLogin(){
        return "log";
    }

    @GetMapping(path="/createAccount")
    public ModelAndView createAccount() {

        ModelAndView mav = new ModelAndView("createAccount");
        mav.addObject("createAccount", "Veuillez saisir vos informations");
        return mav;
    }

    @PostMapping(path="/accountCreated")
    public ModelAndView accountCreated(String firstName, String userEmail, String userPassword, String userLogin) {
        ModelAndView mav3 = new ModelAndView("accountCreated");
        mav3.addObject("confirmationCreationCompte", "Votre compte a bien été créé, connectez-vous à présent.");
        return mav3;

    }

    /*
    @RequestMapping(path="/homeLogged")
    public ModelAndView login(String userLogin, String userPassword) {
        boolean userExists = false;
        boolean userPasswordCorrect = false;
        ModelAndView mav;
        for (UserDto userDto : this.userService.getAll()) {
            if (userDto.getUserLogin().equals(userLogin)) {
                userExists = true;
                if (userDto.getUserPassword().equals(userPassword)) {
                    mav = new ModelAndView("homeLogged");
                    mav.addObject("connecte", "Bienvenue " + userLogin);
                    return mav;
                }
            }
        }
        if (!userExists) {
            mav = new ModelAndView("log");
            mav.addObject("loginInexsitant", "L'identifiant ou le mot de passe est incorrect.");
            return mav;
        }

        if (!userPasswordCorrect) {
            mav = new ModelAndView("log");
            mav.addObject("mdpIncorrect", "Le mot de passe ou identifiant est incorrect. Veuillez réessayer");
            return mav;
        }

        return null;
        }
        */


}
