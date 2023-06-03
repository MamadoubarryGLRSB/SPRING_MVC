package ism.gestion_pedagogique.controllers;

import ism.gestion_pedagogique.entities.Cours;
import ism.gestion_pedagogique.entities.Professeur;
import ism.gestion_pedagogique.entities.SessionCours;
import ism.gestion_pedagogique.repositories.CoursRepository;
import ism.gestion_pedagogique.repositories.ProfesseurRepository;
import ism.gestion_pedagogique.repositories.SessionCoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import java.time.Instant;
import java.time.ZoneId;


@Controller
public class ProfesseurController {
    @Autowired
    ProfesseurRepository professeurRepository;

    @Autowired
    SessionCoursRepository sessionCoursRepository;

    @Autowired
    CoursRepository coursRepository;

    @GetMapping("/liste-professeurs")
    public String getAllProfesseur(Model model) {
        List<Professeur> professeurList = professeurRepository.findAll();
        model.addAttribute("professeurList", professeurList);
        return "professeur";
    }










    @GetMapping("/professeur-cours")
    public String getProfesseurCours(@RequestParam Long professeurId,
                                     @RequestParam(required = false) String filter,
                                     Model model) {
        Professeur professeur = professeurRepository.findById(professeurId).orElse(null);
        if (professeur != null) {
            List<SessionCours> sessionCours;

            if (filter != null && filter.equals("jour")) {
                // Filtrer les cours du professeur pour la journée actuelle
                LocalDate currentDate = LocalDate.now();
                sessionCours = professeur.getSessionCours()
                        .stream()
                        .filter(sc -> Instant.ofEpochMilli(sc.getDate().getTime())
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                                .equals(currentDate))
                        .collect(Collectors.toList());
            } else if (filter != null && filter.equals("semaine")) {
                // Filtrer les cours du professeur pour la semaine actuelle
                LocalDate currentDate = LocalDate.now();
                TemporalField weekField = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
                int currentWeek = currentDate.get(weekField);
                sessionCours = professeur.getSessionCours()
                        .stream()
                        .filter(sc -> Instant.ofEpochMilli(sc.getDate().getTime())
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                                .get(weekField) == currentWeek)
                        .collect(Collectors.toList());
            } else {
                sessionCours = professeur.getSessionCours();
            }

            model.addAttribute("sessionCours", sessionCours);
            model.addAttribute("professeur", professeur);

        }
        return "professeur-cours";
    }


    @GetMapping("/ajouter-professeur")
    public String ajouterProfesseur(Model model) {


        model.addAttribute("professeur", new Professeur());

        return "forme_professeur";

    }
    @PostMapping("/enregistrer-professeur")
    public String enregistrerCours(@ModelAttribute("professeur") Professeur professeur) {

        String specialite=professeur.getSpecialites();
        String grade=professeur.getGrades();
        String nomComplet=professeur.getNomComplet();

        professeurRepository.save(professeur);

        return "redirect:/liste-professeurs";
    }

}
