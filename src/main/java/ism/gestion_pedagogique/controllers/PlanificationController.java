package ism.gestion_pedagogique.controllers;

import ism.gestion_pedagogique.entities.AnneeScolaire;
import ism.gestion_pedagogique.entities.Classe;
import ism.gestion_pedagogique.entities.PlanificationClasse;
import ism.gestion_pedagogique.repositories.AnneeScolaireRepository;
import ism.gestion_pedagogique.repositories.ClasseRepository;
import ism.gestion_pedagogique.repositories.PlanificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class PlanificationController {

    @Autowired
    PlanificationRepository planificationRepository;

    @Autowired
    AnneeScolaireRepository anneeScolaireRepository;

    @Autowired
    ClasseRepository classeRepository;


    @GetMapping("/liste-planifier-classe")
    public String listePlanifierClasse(Model model) {
        List<PlanificationClasse> planifications = planificationRepository.findAll();
        model.addAttribute("planifications", planifications);
        return "planification";
    }

    @GetMapping("/planifier-classe")
    public String afficherFormulairePlanification(Model model) {
        // Récupérer la liste des classes existantes
        List<Classe> classes = classeRepository.findAll();
        List<AnneeScolaire> anneeScolaires = anneeScolaireRepository.findAll();
        // Ajouter la liste des classes au modèle
        model.addAttribute("classes", classes);
        model.addAttribute("anneeScolaires", anneeScolaires);

        // Ajouter un nouvel objet PlanificationClasse au modèle
        model.addAttribute("planificationClasse", new PlanificationClasse());

        return "form-planification";
    }




    @PostMapping("/enregistrer-planification-classe")
    public String enregistrerPlanificationClasse(@ModelAttribute("planification") PlanificationClasse planification) {

        // Récupérer la classe associée à la planification
        Classe classe = classeRepository.findById(planification.getClasse().getId()).orElse(null);
        AnneeScolaire anneeScolaire=anneeScolaireRepository.findById(planification.getAnneeScolaire().getId()).orElse(null);

        // Vérifier si la classe existe
        if (classe == null) {
            // La classe n'existe pas, retourner une erreur
            // Ici on redirige vers la page d'erreur "404 Not Found", mais vous pouvez rediriger vers une autre page de votre choix
            return "redirect:/error/404";
        }

        // Associer la classe à la planification
        planification.setClasse(classe);
        planification.setAnneeScolaire(anneeScolaire);

        // Enregistrer la planification dans la base de données
        planificationRepository.save(planification);

        return "redirect:/liste-planifier-classe";
    }



}
