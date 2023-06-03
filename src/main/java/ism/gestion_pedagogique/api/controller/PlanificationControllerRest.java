package ism.gestion_pedagogique.api.controller;

import ism.gestion_pedagogique.api.dto.PlanificationClasseDTO;
import ism.gestion_pedagogique.api.dto.SessionCoursDTO;
import ism.gestion_pedagogique.entities.*;
import ism.gestion_pedagogique.entities.Module;
import ism.gestion_pedagogique.repositories.AnneeScolaireRepository;
import ism.gestion_pedagogique.repositories.ClasseRepository;
import ism.gestion_pedagogique.repositories.PlanificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/planifications")
public class PlanificationControllerRest {

    @Autowired
    PlanificationRepository planificationRepository;

    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private AnneeScolaireRepository anneeScolaireRepository;

    @GetMapping()
    public ResponseEntity<List<PlanificationClasseDTO>> getAllPlanifications(Model model) {
        List<PlanificationClasse> planifications = planificationRepository.findAll();
        List<PlanificationClasseDTO> planificationDTOs = new ArrayList<>();
        for (PlanificationClasse planification : planifications) {
            planificationDTOs.add(new PlanificationClasseDTO(planification));
        }
        return ResponseEntity.ok(planificationDTOs);
    }

    @GetMapping("/classes")
    public ResponseEntity<List<Classe>> getClasses() {
        List<Classe> classes = classeRepository.findAll();
        return ResponseEntity.ok(classes);
    }

    @GetMapping("/anneeScolaires")
    public ResponseEntity<List<AnneeScolaire>> getSalles() {
        List<AnneeScolaire> anneeScolaires = anneeScolaireRepository.findAll();
        return ResponseEntity.ok(anneeScolaires);
    }

    @PostMapping("/ajouter-planification-classe")
    public ResponseEntity<PlanificationClasseDTO> ajouterPlanificationClasse(@RequestBody PlanificationClasseDTO planificationClasseDTO) {
        PlanificationClasse planificationClasse = new PlanificationClasse();
        planificationClasse.setLibelle(planificationClasseDTO.getLibelle());
        planificationClasse.setNiveau(planificationClasseDTO.getNiveau());

        // Récupérer l'objet Classe à partir de l'identifiant
        Classe classe = classeRepository.findById(planificationClasseDTO.getClasseId()).orElse(null);
        if (classe == null) {
            return ResponseEntity.badRequest().build();
        }
        planificationClasse.setClasse(classe);

        // Récupérer l'objet AnneeScolaire à partir de l'identifiant
        AnneeScolaire anneeScolaire = anneeScolaireRepository.findById(planificationClasseDTO.getAnneeScolaireId()).orElse(null);
        if (anneeScolaire == null) {
            return ResponseEntity.badRequest().build();
        }
        planificationClasse.setAnneeScolaire(anneeScolaire);

        planificationRepository.save(planificationClasse);
        planificationClasseDTO.setId(planificationClasse.getId());

        return ResponseEntity.ok(planificationClasseDTO);
    }


    @PostMapping("/enregistrer-planification-classe")
    public ResponseEntity<PlanificationClasseDTO> enregistrerPlanificationClasse(@RequestBody PlanificationClasseDTO planificationClasseDTO) {
        // Récupérer les objets correspondant aux identifiants sélectionnés dans le DTO
        Classe classe = classeRepository.findById(planificationClasseDTO.getClasseId()).orElse(null);
        AnneeScolaire anneeScolaire = anneeScolaireRepository.findById(planificationClasseDTO.getAnneeScolaireId()).orElse(null);

        // Vérifier que les objets ont été correctement récupérés
        if (classe == null || anneeScolaire == null) {
            return ResponseEntity.badRequest().build();
        }

        // Créer une nouvelle instance de PlanificationClasse et assigner les valeurs du DTO
        PlanificationClasse planificationClasse = new PlanificationClasse();
        planificationClasse.setLibelle(planificationClasseDTO.getLibelle());
        planificationClasse.setNiveau(planificationClasseDTO.getNiveau());
        planificationClasse.setClasse(classe);
        planificationClasse.setAnneeScolaire(anneeScolaire);

        // Enregistrer l'objet PlanificationClasse dans la base de données
        planificationRepository.save(planificationClasse);
        planificationClasseDTO.setId(planificationClasse.getId());

        return ResponseEntity.ok(planificationClasseDTO);
    }



}
