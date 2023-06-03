package ism.gestion_pedagogique.api.controller;

import ism.gestion_pedagogique.api.dto.CoursDTO;
import ism.gestion_pedagogique.entities.*;
import ism.gestion_pedagogique.repositories.CoursRepository;
import ism.gestion_pedagogique.repositories.SemestreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/cours")
public class CoursControllerRest {


    @Autowired
    private CoursRepository coursRepository;


    @Autowired
    private SemestreRepository semestreRepository;
    @GetMapping
    public ResponseEntity<List<CoursDTO>> getAllCours(@RequestParam(required = false) String etat) {
        List<Cours> coursList;
        if (etat != null) {
            coursList = coursRepository.findByEtat(etat);
        } else {
            coursList = coursRepository.findAll();
        }
        List<CoursDTO> coursDTOs = new ArrayList<>();
        for (Cours cours : coursList) {
            coursDTOs.add(new CoursDTO(cours));
        }
        return ResponseEntity.ok(coursDTOs);
    }

    @PostMapping("/ajouter-cours")
    public ResponseEntity<CoursDTO> ajouterCours(@RequestBody CoursDTO coursDTO) {
        Cours cours = new Cours();
        cours.setNbreHeures(coursDTO.getNbreHeures());
        cours.setRaisonAnnulation(coursDTO.getRaisonAnnulation());
        cours.setEtat("En Cours");
        cours.setNom(coursDTO.getNom());
        cours.setAnnule(coursDTO.isAnnule());

        // Récupérer l'objet lié (semestre) à partir de l'identifiant
        Semestre semestre = semestreRepository.findById(coursDTO.getSemestreId()).orElse(null);

        // Vérifier si le semestre a été récupéré avec succès
        if (semestre == null) {
            return ResponseEntity.badRequest().build();
        }

        cours.setSemestre(semestre);
        coursRepository.save(cours);
        coursDTO.setId(cours.getId());

        return ResponseEntity.ok(coursDTO);
    }


    @GetMapping("/semestres")
    public ResponseEntity<List<Semestre>> getSemestres() {
        List<Semestre> semestres = semestreRepository.findAll();
        return ResponseEntity.ok(semestres);
    }

    @PostMapping("/enregistrer-cours")
    public ResponseEntity<CoursDTO> enregistrerCours(@RequestBody CoursDTO coursDTO) {
        // Créer une nouvelle instance de Cours et assigner les valeurs du DTO
        Cours cours = new Cours();
        cours.setNbreHeures(coursDTO.getNbreHeures());
        cours.setRaisonAnnulation(coursDTO.getRaisonAnnulation());
        cours.setEtat("En Cours");
        cours.setNom(coursDTO.getNom());
        cours.setAnnule(coursDTO.isAnnule());

        // Récupérer l'objet Semestre à partir de son identifiant
        Semestre semestre = semestreRepository.findById(coursDTO.getSemestreId()).orElse(null);

        // Vérifier que l'objet Semestre a été correctement récupéré
        if (semestre == null) {
            return ResponseEntity.badRequest().build();
        }

        cours.setSemestre(semestre);

        // Enregistrer l'objet Cours dans la base de données
        coursRepository.save(cours);

        // Mettre à jour l'identifiant dans le DTO
        coursDTO.setId(cours.getId());

        return ResponseEntity.ok(coursDTO);
    }


    @PutMapping("/cours/{id}/etat")
    public ResponseEntity<String> changerEtatSessionCours(@PathVariable Long id, @RequestBody String nouvelEtat) {
        Cours cours = coursRepository.findById(id).orElse(null);
        if (cours != null) {
            cours.setEtat(nouvelEtat);
            coursRepository.save(cours);
            return ResponseEntity.ok("État de la session de cours modifié avec succès.");
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/cours/{id}/terminer")
    public ResponseEntity<String> changerEtatSessionCoursTerminer(@PathVariable Long id, @RequestBody String nouvelEtat) {
        Cours cours = coursRepository.findById(id).orElse(null);
        if (cours != null) {
            cours.setEtat(nouvelEtat);
            coursRepository.save(cours);
            return ResponseEntity.ok("État de la session de cours modifié avec succès.");
        }
        return ResponseEntity.notFound().build();
    }







}
