package ism.gestion_pedagogique.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {


    @GetMapping("/403")
    public String error403(){
        return "errors/403";
    }
    @GetMapping("/404")
    public String error404(){
        return "errors/403";
    }
}
