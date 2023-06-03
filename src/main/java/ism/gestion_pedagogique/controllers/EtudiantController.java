package ism.gestion_pedagogique.controllers;


import ism.gestion_pedagogique.entities.Etudiant;
import ism.gestion_pedagogique.repositories.EtudiantRepository;
import ism.gestion_pedagogique.repositories.SessionCoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;



@Controller

public class EtudiantController {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @GetMapping("/etudiants")
    public String listEtudiants(Model model, @RequestParam(name = "size",defaultValue = "3") int size,
                                @RequestParam(name="page", defaultValue = "0") int page
                                ) {
        Page<Etudiant> etudiants = etudiantRepository.findAll( PageRequest.of(page,size));
        model.addAttribute("etudiants", etudiants.getContent());
        model.addAttribute("pages",new int[etudiants.getTotalPages()]);
        model.addAttribute("currentPages",page);

        return "etudiant_liste"; // Remplacez "etudiant/liste" par le nom de votre vue
    }

}
