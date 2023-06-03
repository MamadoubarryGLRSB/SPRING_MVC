package ism.gestion_pedagogique.controllers;

import ism.gestion_pedagogique.entities.Classe;

import ism.gestion_pedagogique.entities.Module;
import ism.gestion_pedagogique.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ModuleController {
    @Autowired
    private ModuleRepository moduleRepository;


    @GetMapping("/liste-modules")
    public String getAllModules(Model model) {
        List<Module> modules = moduleRepository.findAll();
        model.addAttribute("modules", modules);
        return "module";
    }

    @GetMapping("/ajouter-module")
    public String ajouterModule(Model model) {

        model.addAttribute("module", new Module());

        return "forme_module";

    }

    @PostMapping("/enregistrer-module")
    public String enregistrerModule(@ModelAttribute("module") Module module) {

        String libelle=module.getLibelle();

        moduleRepository.save(module);

        return "redirect:/liste-modules";
    }

}
