package ism.gestion_pedagogique.controllers;

import ism.gestion_pedagogique.entities.*;
import ism.gestion_pedagogique.entities.Module;
import ism.gestion_pedagogique.repositories.ClasseRepository;
import ism.gestion_pedagogique.repositories.CoursRepository;
import ism.gestion_pedagogique.repositories.SemestreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



import java.util.List;

@Controller
public class CoursController {

    @Autowired
    private CoursRepository coursRepository;

    @Autowired
    SemestreRepository semestreRepository;


    @GetMapping("/liste-cours")
    public String getAllCours(@RequestParam(required = false) String etat, Model model) {
        List<Cours> cours;

        if (etat != null) {
            cours = coursRepository.findByEtat(etat);
        } else {
            cours = coursRepository.findAll();
        }

        model.addAttribute("cours", cours);
        return "cours";
    }


    // Contrôleur
    @PostMapping("/demande-justification")
    public String demandeJustification(@RequestParam("coursId") Long coursId, @RequestParam("justification") String justification) {
        Cours cours = coursRepository.findById(coursId).orElse(null);
        if (cours == null) {
            // Gérer le cas où le cours n'est pas trouvé
            return "redirect:/erreur";
        }

        cours.setAnnule(true); // Définir l'état d'annulation sur true uniquement pour le cours spécifique
        cours.setRaisonAnnulation(justification);
        coursRepository.save(cours);

        // Redirection vers une autre page ou une page de confirmation
        return "confirmation-annulation";
    }

    @PostMapping("/annuler-demande")
    public String annulerDemande(@RequestParam("coursId") Long coursId) {
        Cours cours = coursRepository.findById(coursId).orElse(null);
        if (cours == null) {
            // Gérer le cas où le cours n'est pas trouvé
            return "redirect:/erreur";
        }

        cours.setAnnule(false); // Définir l'état d'annulation sur false
        coursRepository.save(cours);

        // Redirection vers une autre page ou une page de confirmation
        return "confirmation-annulation";
    }




    @GetMapping("/planif-cours")
    public String planifCours(Model model) {
        // Récupérer la liste des cours, des salles, des modules et des professeurs
        List<Semestre> semestreList = semestreRepository.findAll();
        // Ajouter les listes au modèle
        model.addAttribute("semestreList", semestreList);
        // Ajouter un nouvel objet SessionCours vide pour être rempli par l'utilisateur
        model.addAttribute("cours", new Cours());

        return "form-cours";

    }

    @PostMapping("/enregist-cours")
    public String enregistCours(@ModelAttribute("cours") Cours cours) {
        // Récupérer les objets correspondant aux identifiants sélectionnés dans la vue
        Semestre semestre = semestreRepository.findById(cours.getSemestre().getId()).orElse(null);
        // Affecter les objets récupérés à l'objet SessionCours
        cours.setSemestre(semestre);
        int nbreHeures = cours.getNbreHeures();
        String etat=cours.getEtat();
        cours.setEtat("En Cours");
        String nom=cours.getNom();

        // Affecter les horaires générés à l'objet SessionCours
        // Enregistrer l'objet SessionCours dans la base de données
        coursRepository.save(cours);

        return "redirect:/liste-cours";
    }

    @PostMapping("/terminer")
    public String terminerSession(@RequestParam Long sessionId) {
        Cours cours = coursRepository.findById(sessionId).orElse(null);
        if (cours != null) {
            cours.setEtat("Terminé");
            coursRepository.save(cours);
        }
        return "redirect:/liste-cours";
    }

    @PostMapping("/annuler")
    public String annulerSession(@RequestParam Long sessionId) {
        Cours cours = coursRepository.findById(sessionId).orElse(null);
        if (cours != null) {
            cours.setEtat("Annuler");
            coursRepository.save(cours);
        }
        return "redirect:/liste-cours";
    }




}
