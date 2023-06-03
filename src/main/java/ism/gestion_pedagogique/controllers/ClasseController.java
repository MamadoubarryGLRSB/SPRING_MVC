package ism.gestion_pedagogique.controllers;

import ism.gestion_pedagogique.entities.*;
import ism.gestion_pedagogique.entities.Module;
import ism.gestion_pedagogique.repositories.ClasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class ClasseController {
    @Autowired
    private ClasseRepository classeRepository;


    @GetMapping("/liste-classes")
    public String getAllClasses(Model model) {
        List<Classe> classes = classeRepository.findAll();
        model.addAttribute("classes", classes);
        return "classe";
    }

    @GetMapping("/ajouter-classe")
    public String ajouterClasse(Model model) {


        model.addAttribute("classe", new Classe());

        return "forme_classe";

    }

    @PostMapping("/enregistrer-classe")
    public String enregistrerCours(@ModelAttribute("classe") Classe classe) {

        String libelle=classe.getLibelle();
        String niveau=classe.getNiveau();
        String filiere=classe.getFiliere();

        classeRepository.save(classe);

        return "redirect:/liste-classes";
    }



}
