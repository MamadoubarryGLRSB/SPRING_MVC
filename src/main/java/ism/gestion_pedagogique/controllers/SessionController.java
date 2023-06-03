package ism.gestion_pedagogique.controllers;

import ism.gestion_pedagogique.entities.*;
import ism.gestion_pedagogique.entities.Module;
import ism.gestion_pedagogique.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller

public class SessionController {

    @Autowired
    private SessionCoursRepository sessionCoursRepository;

    @Autowired
    private CoursRepository coursRepository;

    @Autowired
    private SalleRepository salleRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private ProfesseurRepository professeurRepository;




    @GetMapping("/liste-session-cours")
    public String afficherSessionsCours(Model model, @RequestParam(name="professeurId", required=false) Long professeurId,
                                        @RequestParam(name="moduleId", required=false) Long moduleId,
                                        @RequestParam(name="size", defaultValue = "5") int size,
                                        @RequestParam(name="page", defaultValue = "0") int page)

    {
        Page<SessionCours> sessionCoursList;
        if (professeurId != null && moduleId==null) {

            sessionCoursList = sessionCoursRepository.findByProfesseur(new Professeur(professeurId),  PageRequest.of(page,size));
        } else if (moduleId != null && professeurId==null) {
            sessionCoursList = sessionCoursRepository.findByModule(new Module(moduleId), PageRequest.of(page,size));
        } else {
            sessionCoursList = sessionCoursRepository.findAll( PageRequest.of(page,size));
        }

        model.addAttribute("sessionCoursList", sessionCoursList.getContent());
        model.addAttribute("pages",new int[sessionCoursList.getTotalPages()]);
        model.addAttribute("currentPages",page);
        model.addAttribute("professeurList",professeurRepository.findAll());
        model.addAttribute("moduleList",moduleRepository.findAll());

        return "session_cours";
    }

    @GetMapping("/")
    public  String index(){
        return "redirect:/liste-session-cours";
    }




    @GetMapping("/planifier-cours")
    public String planifierCours(Model model) {
        // Récupérer la liste des cours, des salles, des modules et des professeurs

        List<Salle> salleList = salleRepository.findAll();
        List<Module> moduleList = moduleRepository.findAll();
        List<Professeur> professeurList = professeurRepository.findAll();
        List<Cours> coursList = coursRepository.findAll();

        // Ajouter les listes au modèle

        model.addAttribute("salleList", salleList);
        model.addAttribute("moduleList", moduleList);
        model.addAttribute("professeurList", professeurList);
        model.addAttribute("coursList", coursList);

        // Ajouter un nouvel objet SessionCours vide pour être rempli par l'utilisateur
        model.addAttribute("sessionCours", new SessionCours());

        return "form.sessions_cours";

    }



    // Action pour valider une session de cours
    @PostMapping("/sessions-cours/valider")
    public String validerSession(@RequestParam Long sessionId) {
        SessionCours sessionCours = sessionCoursRepository.findById(sessionId).orElse(null);
        if (sessionCours != null) {
            sessionCours.setEtat("Validée");
            sessionCoursRepository.save(sessionCours);
        }
        return "redirect:/";
    }

    // Action pour invalider une session de cours
    @PostMapping("/sessions-cours/invalider")
    public String invaliderSession(@RequestParam Long sessionId) {
        SessionCours sessionCours = sessionCoursRepository.findById(sessionId).orElse(null);
        if (sessionCours != null) {
            sessionCours.setEtat("Invalidée");
            sessionCoursRepository.save(sessionCours);
        }
        return "redirect:/";
    }


    @PostMapping("/enregistrer-cours")
    public String enregistrerCours(@ModelAttribute("sessionCours") SessionCours sessionCours) {
        // Récupérer les objets correspondant aux identifiants sélectionnés dans la vue

        Salle salle = salleRepository.findById(sessionCours.getSalle().getId()).orElse(null);
        Module module = moduleRepository.findById(sessionCours.getModule().getId()).orElse(null);
        Professeur professeur = professeurRepository.findById(sessionCours.getProfesseur().getId()).orElse(null);
        Cours cours = coursRepository.findById(sessionCours.getCours().getId()).orElse(null);
        // Vérifier que les objets ont été correctement récupérés
        if (cours==null || salle == null || module == null || professeur == null) {
            return "redirect:/sessions-cours/planifier-cours";
        }

        // Affecter les objets récupérés à l'objet SessionCours

        sessionCours.setSalle(salle);
        sessionCours.setModule(module);
        sessionCours.setProfesseur(professeur);
        sessionCours.setCours(cours);



        // Générer les horaires de début et de fin en fonction du nombre d'heures et de l'heure de début sélectionnés
        int heureDebut = sessionCours.getHeureDebut();
        int nbreHeures = cours.getNbreHeures();
        String etat=sessionCours.getEtat();
        sessionCours.setEtat("En Cours");
        sessionCours.setNbreHeures(nbreHeures);
        // Affecter les horaires générés à l'objet SessionCours


        // Enregistrer l'objet SessionCours dans la base de données
        sessionCoursRepository.save(sessionCours);

        return "redirect:/";
    }
}