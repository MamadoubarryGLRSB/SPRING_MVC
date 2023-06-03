package ism.gestion_pedagogique.controllers;

import ism.gestion_pedagogique.entities.AnneeScolaire;
import ism.gestion_pedagogique.entities.Classe;
import ism.gestion_pedagogique.repositories.AnneeScolaireRepository;
import ism.gestion_pedagogique.repositories.ClasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AnneeScolaireController {

    @Autowired
    private AnneeScolaireRepository anneeScolaireRepository;


    @GetMapping("/liste-annees-scolaire")
    public String getAllAnnee(Model model) {
        List<AnneeScolaire> anneeScolaires = anneeScolaireRepository.findAll();
        model.addAttribute("anneeScolaires", anneeScolaires);
        return "annee-scolaire";
    }

    @GetMapping("/ajouter-annee-scolaire")
    public String ajouterAnnee(Model model) {


        model.addAttribute("anneeScolaire", new AnneeScolaire());

        return "forme-annee-scolaire";

    }

    @PostMapping("/enregistrer-annees")
    public String enregistrerCours(@ModelAttribute("annee-scolaire") AnneeScolaire anneeScolaire ) {
        anneeScolaireRepository.save(anneeScolaire);

        return "redirect:/liste-annees-scolaire";
    }
}
