package ism.gestion_pedagogique.controllers;


import ism.gestion_pedagogique.entities.Absence;
import ism.gestion_pedagogique.entities.Etudiant;
import ism.gestion_pedagogique.repositories.AbsenceRepository;
import ism.gestion_pedagogique.repositories.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AbsenceController {
    @Autowired
    private AbsenceRepository absenceRepository;
    @GetMapping("/absences")
    public String listEtudiants(Model model, @RequestParam(name = "size",defaultValue = "3") int size,
                                @RequestParam(name="page", defaultValue = "0") int page
    ) {
        Page<Absence> absences = absenceRepository.findAll( PageRequest.of(page,size));
        model.addAttribute("absences", absences.getContent());
        model.addAttribute("pages",new int[absences.getTotalPages()]);
        model.addAttribute("currentPages",page);

        return "absence_liste";
    }
}
