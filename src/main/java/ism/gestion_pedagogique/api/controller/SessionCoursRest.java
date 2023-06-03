package ism.gestion_pedagogique.api.controller;

import ism.gestion_pedagogique.api.dto.SessionCoursDTO;
import ism.gestion_pedagogique.entities.*;
import ism.gestion_pedagogique.entities.Module;
import ism.gestion_pedagogique.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/sessions-cours")
public class SessionCoursRest {
    @Autowired
    private  SessionCoursRepository sessionCoursRepository;
    @Autowired
    private CoursRepository coursRepository;
    @Autowired
    private SalleRepository salleRepository;
    @Autowired
    private ModuleRepository moduleRepository;
    @Autowired
    private ProfesseurRepository professeurRepository;

    @Autowired
    private  ClasseRepository classeRepository;

    @GetMapping
    public ResponseEntity<List<SessionCoursDTO>> getSessionsCours() {
        List<SessionCours> sessionCoursList = sessionCoursRepository.findAll();
        List<SessionCoursDTO> sessionCoursDTOList = sessionCoursList.stream()
                .filter(sessionCours -> sessionCours.getClasse() != null) // Filtrer les objets avec une référence non nulle vers la classe
                .map(SessionCoursDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(sessionCoursDTOList);
    }


    @GetMapping("/modules")
    public ResponseEntity<List<Module>> getModules() {
        List<Module> modules = moduleRepository.findAll();
        return ResponseEntity.ok(modules);
    }

    @GetMapping("/cours")
    public ResponseEntity<List<Cours>> getCours() {
        List<Cours> cours = coursRepository.findAll();
        return ResponseEntity.ok(cours);
    }

    @GetMapping("/professeurs")
    public ResponseEntity<List<Professeur>> getProfesseurs() {
        List<Professeur> professeurs = professeurRepository.findAll();
        return ResponseEntity.ok(professeurs);
    }

    @GetMapping("/salles")
    public ResponseEntity<List<Salle>> getSalles() {
        List<Salle> salles = salleRepository.findAll();
        return ResponseEntity.ok(salles);
    }

    @GetMapping("/classes")
    public ResponseEntity<List<Classe>> getClasses() {
        List<Classe> classes = classeRepository.findAll();
        return ResponseEntity.ok(classes);
    }


    @PostMapping("/ajouter-session-cours")
    public ResponseEntity<SessionCoursDTO> ajouterSessionCours(@RequestBody SessionCoursDTO sessionCoursDTO) {
        // Vérification des données requises
        if (sessionCoursDTO.getCoursId() == null || sessionCoursDTO.getSalleId() == null
                || sessionCoursDTO.getModuleId() == null || sessionCoursDTO.getProfesseurId() == null
                || sessionCoursDTO.getClassesId() == null) {
            return ResponseEntity.badRequest().build();
        }

        // Création d'une nouvelle session de cours à partir des données DTO
        SessionCours sessionCours = new SessionCours();
        sessionCours.setDate(sessionCoursDTO.getDate());
        sessionCours.setHeureDebut(sessionCoursDTO.getHeureDebut());
        sessionCours.setNbreHeures(sessionCoursDTO.getNbreHeures());
        sessionCours.setEtat(sessionCoursDTO.getEtat());
        sessionCours.setEtat_session(sessionCoursDTO.getEtat_session());
        sessionCours.setDemandeAnnulation(sessionCoursDTO.isDemandeAnnulation());

        // Récupération des objets liés à partir des identifiants

        Salle salle = salleRepository.findById(sessionCoursDTO.getSalleId()).orElse(null);
        Module module = moduleRepository.findById(sessionCoursDTO.getModuleId()).orElse(null);
        Professeur professeur = professeurRepository.findById(sessionCoursDTO.getProfesseurId()).orElse(null);
        Classe classe = classeRepository.findById(sessionCoursDTO.getClassesId()).orElse(null);
        Cours cours = coursRepository.findById(sessionCoursDTO.getCoursId()).orElse(null);
        // Vérification que les objets liés ont été récupérés avec succès
        if (cours == null || salle == null || module == null || professeur == null || classe == null) {
            return ResponseEntity.badRequest().build();
        }

        // Association des objets liés à la session de cours
        sessionCours.setCours(cours);
        sessionCours.setSalle(salle);
        sessionCours.setModule(module);
        sessionCours.setProfesseur(professeur);
        sessionCours.setClasse(classe);

        // Enregistrement de la session de cours dans la base de données
        sessionCoursRepository.save(sessionCours);

        // Mise à jour de l'identifiant dans le DTO
        sessionCoursDTO.setId(sessionCours.getId());

        // Retour de la session de cours ajoutée en tant que réponse JSON
        return ResponseEntity.status(HttpStatus.CREATED).body(sessionCoursDTO);

    }




    @PostMapping("/enregistrer-session-cours")
    public ResponseEntity<SessionCoursDTO> enregistrerSessionCours(@RequestBody SessionCoursDTO sessionCoursDTO) {
        // Récupérer les objets correspondant aux identifiants sélectionnés dans le DTO
        Salle salle = salleRepository.findById(sessionCoursDTO.getSalleId()).orElse(null);
        Module module = moduleRepository.findById(sessionCoursDTO.getModuleId()).orElse(null);
        Professeur professeur = professeurRepository.findById(sessionCoursDTO.getProfesseurId()).orElse(null);
        Cours cours = coursRepository.findById(sessionCoursDTO.getCoursId()).orElse(null);
        Classe classe = classeRepository.findById(sessionCoursDTO.getClassesId()).orElse(null);

        // Vérifier que les objets ont été correctement récupérés
        if (cours == null || salle == null || module == null || professeur == null || classe==null) {
            return ResponseEntity.badRequest().build();
        }

        // Créer une nouvelle instance de SessionCours et assigner les valeurs du DTO
        SessionCours sessionCours = new SessionCours();
        sessionCours.setSalle(salle);
        sessionCours.setModule(module);
        sessionCours.setProfesseur(professeur);
        sessionCours.setClasse(classe);
        sessionCours.setCours(cours);
        sessionCours.setHeureDebut(sessionCoursDTO.getHeureDebut());
        sessionCours.setEtat_session("En Presentiel");
        sessionCours.setNbreHeures(cours.getNbreHeures());
        sessionCours.setEtat("En Cours");


        // Assigner la valeur de la date au modèle SessionCours
        sessionCours.setDate(sessionCoursDTO.getDate());

        // Enregistrer l'objet SessionCours dans la base de données
        sessionCoursRepository.save(sessionCours);
        sessionCoursDTO.setId(sessionCours.getId());

        return ResponseEntity.ok(sessionCoursDTO);
    }


    @PutMapping("/sessions-cours/{id}/etat")
    public ResponseEntity<String> changerEtatSessionCours(@PathVariable Long id, @RequestBody String nouvelEtat) {
        SessionCours sessionCours = sessionCoursRepository.findById(id).orElse(null);
        if (sessionCours != null) {
            sessionCours.setEtat(nouvelEtat);
            sessionCoursRepository.save(sessionCours);
            return ResponseEntity.ok("État de la session de cours modifié avec succès.");
        }
        return ResponseEntity.notFound().build();
    }




    @PutMapping("/sessions-cours/{id}/session")
    public ResponseEntity<String> changerSession(@PathVariable Long id, @RequestBody String nouvelEtat) {
        SessionCours sessionCours = sessionCoursRepository.findById(id).orElse(null);
        if (sessionCours != null) {
            sessionCours.setEtat_session(nouvelEtat);

            sessionCoursRepository.save(sessionCours);
            return ResponseEntity.ok("État de la session de cours modifié avec succès.");
        }
        return ResponseEntity.notFound().build();
    }




    @PutMapping("/update-session-cours/{id}")
    public ResponseEntity<SessionCoursDTO> updateSessionCours(@PathVariable("id") Long id, @RequestBody SessionCoursDTO sessionCoursDTO) {
        Optional<SessionCours> optionalSessionCours = sessionCoursRepository.findById(id);
        if (optionalSessionCours.isPresent()) {
            SessionCours sessionCours = optionalSessionCours.get();
            sessionCours.setDate(sessionCoursDTO.getDate());
            sessionCours.setHeureDebut(sessionCoursDTO.getHeureDebut());
            sessionCours.setNbreHeures(sessionCoursDTO.getNbreHeures());
            sessionCours.setEtat(sessionCoursDTO.getEtat());

            // Récupérer les objets liés (cours, salle, module, professeur) à partir des identifiants
            Cours cours = coursRepository.findById(sessionCoursDTO.getCoursId()).orElse(null);
            Salle salle = salleRepository.findById(sessionCoursDTO.getSalleId()).orElse(null);
            Module module = moduleRepository.findById(sessionCoursDTO.getModuleId()).orElse(null);
            Professeur professeur = professeurRepository.findById(sessionCoursDTO.getProfesseurId()).orElse(null);
            Classe classe=classeRepository.findById(sessionCoursDTO.getClassesId() ).orElse(null);
            // Vérifier que les objets liés ont été récupérés avec succès
            if (cours == null || salle == null || module == null || professeur == null) {
                return ResponseEntity.badRequest().build();
            }

            sessionCours.setCours(cours);
            sessionCours.setSalle(salle);
            sessionCours.setModule(module);
            sessionCours.setProfesseur(professeur);
            sessionCours.setClasse(classe);

            sessionCoursRepository.save(sessionCours);
            sessionCoursDTO.setId(sessionCours.getId());

            return ResponseEntity.ok(sessionCoursDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<SessionCoursDTO> getSessionCours(@PathVariable("id") Long id) {
        Optional<SessionCours> optionalSessionCours = sessionCoursRepository.findById(id);
        if (optionalSessionCours.isPresent()) {
            SessionCours sessionCours = optionalSessionCours.get();
            SessionCoursDTO sessionCoursDTO = new SessionCoursDTO(sessionCours);
            return ResponseEntity.ok(sessionCoursDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete-session-cours/{id}")
    public ResponseEntity<Void> deleteSessionCours(@PathVariable("id") Long id) {
        Optional<SessionCours> optionalSessionCours = sessionCoursRepository.findById(id);
        if (optionalSessionCours.isPresent()) {
            sessionCoursRepository.delete(optionalSessionCours.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PutMapping("/sessions-cours/{id}/demande-annulation")
    public ResponseEntity<String> demanderAnnulationCours(@PathVariable Long id) {
        Optional<SessionCours> optionalSessionCours = sessionCoursRepository.findById(id);
        if (optionalSessionCours.isPresent()) {
            SessionCours sessionCours = optionalSessionCours.get();
            sessionCours.setDemandeAnnulation(true);
            sessionCoursRepository.save(sessionCours);
            return ResponseEntity.ok("Demande d'annulation de cours envoyée avec succès.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
