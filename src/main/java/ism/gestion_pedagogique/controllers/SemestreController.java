package ism.gestion_pedagogique.controllers;

import ism.gestion_pedagogique.api.dto.SemestreDTO;
import ism.gestion_pedagogique.entities.Semestre;

import ism.gestion_pedagogique.repositories.SemestreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
public class SemestreController {
    @Autowired
    private SemestreRepository semestreRepository;


    @GetMapping("/liste-semestres")
    public String getAllSemestre(Model model) {
        List<Semestre> semestres = semestreRepository.findAll();
        model.addAttribute("semestres", semestres);
        return "semestres";
    }

    @GetMapping("/ajouter-semestre")
    public String ajouterSemestre(Model model) {

        model.addAttribute("semestre", new Semestre());
        return "forme_semestre";

    }

    @PostMapping("/enregistrer-semestre")
    public String enregistrerSemestre(@ModelAttribute("semestre") Semestre semestre) {
        String libelle=semestre.getLibelle();
        semestreRepository.save(semestre);
        return "redirect:/liste-semestres";
}
}


