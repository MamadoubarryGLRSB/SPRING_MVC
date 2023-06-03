package ism.gestion_pedagogique.api.controller;

import ism.gestion_pedagogique.Security.entities.AppRole;
import ism.gestion_pedagogique.Security.repositories.AppRoleRepository;
import ism.gestion_pedagogique.Security.service.SecurityService;
import ism.gestion_pedagogique.api.dto.ProfesseurDTO;

import ism.gestion_pedagogique.api.dto.SessionCoursDTO;
import ism.gestion_pedagogique.entities.Professeur;
import ism.gestion_pedagogique.entities.Salle;
import ism.gestion_pedagogique.entities.SessionCours;
import ism.gestion_pedagogique.repositories.ProfesseurRepository;
import ism.gestion_pedagogique.repositories.SessionCoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/professeurs")
@CrossOrigin("http://localhost:4200")
public class ProfesseurControllerRest {
    @Autowired
    ProfesseurRepository professeurRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private SecurityService service;

    @Autowired
    private AppRoleRepository rolerepo;

    @Autowired
    private SessionCoursRepository sessionCoursRepository;
    @GetMapping
    public ResponseEntity<List<ProfesseurDTO>> getAllProfesseurs() {
        List<Professeur> professeurList = professeurRepository.findAll();
        List<ProfesseurDTO> professeurDTOList = new ArrayList<>();

        for (Professeur professeur : professeurList) {
            professeurDTOList.add(new ProfesseurDTO(professeur));
        }

        return ResponseEntity.ok(professeurDTOList);
    }



    @PostMapping("/ajouter-professeur")
    public ResponseEntity<ProfesseurDTO> ajouterProfesseur(@RequestBody ProfesseurDTO professeurDTO) {
        // Création d'une instance de l'entité Professeur
        Professeur professeur = new Professeur();
        professeur.setUsername(professeurDTO.getUsername());
        professeur.setPassword(passwordEncoder.encode(professeurDTO.getPassword())); // Hashage du mot de passe
        professeur.setSpecialites(professeurDTO.getSpecialites());
        professeur.setGrades(professeurDTO.getGrades());
        professeur.setNomComplet(professeurDTO.getNomComplet());

        // Enregistrement du professeur dans la base de données
        professeur = professeurRepository.save(professeur);

        // Enregistrement des rôles associés au professeur
        List<AppRole> roles = professeurDTO.getRoles();
        if (roles != null && !roles.isEmpty()) {
            for (AppRole role : roles) {
                rolerepo.save(role);
            }
        }

        // Mise à jour de l'ID du professeur dans le DTO
        professeurDTO.setId(professeur.getId());

        // Retourne le DTO du professeur ajouté avec le code de statut 200 (OK)
        return ResponseEntity.ok(professeurDTO);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<AppRole>> getRoles() {
        List<AppRole> roles = rolerepo.findAll();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/professeur/{id}")
    public ResponseEntity<ProfesseurDTO> getProfesseur(@PathVariable("id") Long id) {
        Optional<Professeur> optionalProfesseur = professeurRepository.findById(id);
        if (optionalProfesseur.isPresent()) {
            Professeur professeur = optionalProfesseur.get();
            ProfesseurDTO professeurDTO = new ProfesseurDTO(professeur);
            return ResponseEntity.ok(professeurDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete-professeur/{id}")
    public ResponseEntity<Void> deleteProfesseur(@PathVariable("id") Long id) {
        Optional<Professeur> optionalProfesseur = professeurRepository.findById(id);
        if (optionalProfesseur.isPresent()) {
            professeurRepository.delete(optionalProfesseur.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
}

    @GetMapping("/professeur-cours")
    public ResponseEntity<Map<String, Object>> getProfesseurCours(@RequestParam Long professeurId,
                                                                  @RequestParam(required = false) String filter) {
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

            Map<String, Object> response = new HashMap<>();
            response.put("sessionCours", sessionCours);
            response.put("professeur", professeur);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }









}
