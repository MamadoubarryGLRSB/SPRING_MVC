package ism.gestion_pedagogique.api.controller;

import ism.gestion_pedagogique.Security.entities.AppRole;
import ism.gestion_pedagogique.Security.repositories.AppRoleRepository;
import ism.gestion_pedagogique.api.dto.CoursDTO;
import ism.gestion_pedagogique.api.dto.EtudiantDTO;
import ism.gestion_pedagogique.api.dto.SessionCoursDTO;
import ism.gestion_pedagogique.entities.*;
import ism.gestion_pedagogique.repositories.ClasseRepository;
import ism.gestion_pedagogique.repositories.CoursEtudiantRepository;
import ism.gestion_pedagogique.repositories.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/etudiants")
public class EtudiantControllerRest {

    @Autowired
    private  EtudiantRepository etudiantRepository;

    @Autowired
    private AppRoleRepository rolerepo;
    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    private ClasseRepository classeRepository;


    @Autowired
    private CoursEtudiantRepository coursEtudiantRepository;

    @GetMapping
    public ResponseEntity<List<EtudiantDTO>> getAllEtudiants() {
        List<Etudiant> etudiantList = etudiantRepository.findAll();
        List<EtudiantDTO> etudiantDTOList = new ArrayList<>();

        for (Etudiant etudiant : etudiantList) {
            etudiantDTOList.add(new EtudiantDTO(etudiant));
        }

        return ResponseEntity.ok(etudiantDTOList);
    }






    @GetMapping("/classes")
    public ResponseEntity<List<Classe>> getClasses() {
        List<Classe> classes = classeRepository.findAll();
        return ResponseEntity.ok(classes);
    }

    @PostMapping("/ajouter-etudiant")
    public ResponseEntity<EtudiantDTO> ajouterEtudiant(@RequestBody EtudiantDTO etudiantDTO) {
        // Création d'une instance de l'entité Etudiant
        Etudiant etudiant = new Etudiant();
        etudiant.setUsername(etudiantDTO.getUsername());
        etudiant.setPassword(passwordEncoder.encode(etudiantDTO.getPassword())); // Hashage du mot de passe
        etudiant.setMatricule(etudiantDTO.getMatricule());
        etudiant.setNomComplet(etudiantDTO.getNomComplet());

        // Enregistrement de l'étudiant dans la base de données
        etudiant = etudiantRepository.save(etudiant);

        // Enregistrement des rôles associés à l'étudiant
        List<AppRole> roles = etudiantDTO.getRoles();
        if (roles != null && !roles.isEmpty()) {
            for (AppRole role : roles) {
                rolerepo.save(role);
            }
        }

        // Mise à jour de l'ID de l'étudiant dans le DTO
        etudiantDTO.setId(etudiant.getId());

        // Retourne le DTO de l'étudiant ajouté avec le code de statut 200 (OK)
        return ResponseEntity.ok(etudiantDTO);
    }

    @DeleteMapping("/delete-etudiant/{id}")
    public ResponseEntity<Void> deleteEtu(@PathVariable("id") Long id) {
        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(id);
        if (optionalEtudiant.isPresent()) {
            etudiantRepository.delete(optionalEtudiant.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    @GetMapping("/classe/{classeId}")
    public ResponseEntity<List<EtudiantDTO>> getEtudiantsByClasse(@PathVariable Long classeId) {
        Classe classe = classeRepository.findById(classeId).orElse(null);

        if (classe == null) {
            // Gérer le cas où la classe n'existe pas
            return ResponseEntity.notFound().build();
        }

        List<Etudiant> etudiantList = classe.getClasseEtudiants().stream().map(c-> c.getEtudiant()).collect(Collectors.toList());
        List<EtudiantDTO> etudiantDTOList = new ArrayList<>();

        for (Etudiant etudiant : etudiantList) {
            etudiantDTOList.add(new EtudiantDTO(etudiant));
        }

        return ResponseEntity.ok(etudiantDTOList);
    }


    @GetMapping("/roles")
    public ResponseEntity<List<AppRole>> getRoles() {
        List<AppRole> roles = rolerepo.findAll();
        return ResponseEntity.ok(roles);
    }



    @GetMapping("/{etudiantId}/sessions-cours")
    public ResponseEntity<List<SessionCoursDTO>> getSessionsCoursByEtudiant(@PathVariable Long etudiantId) {

        Etudiant etudiant = etudiantRepository.findById(etudiantId).orElse(null);

        if (etudiant == null) {
            // Gérer le cas où l'étudiant n'existe pas
            return ResponseEntity.notFound().build();
        }

        List<Etudiant_Sessions_Cours> etudiantSessionsCours = etudiant.getEtudiantSessionsCours();
        System.out.printf(""+etudiantSessionsCours.size());
        List<SessionCoursDTO> sessionCoursDTOList = new ArrayList<>();

        for (Etudiant_Sessions_Cours etudiantSessionCours : etudiantSessionsCours) {
            SessionCours sessionCours = etudiantSessionCours.getSessionCours();
            sessionCoursDTOList.add(new SessionCoursDTO(sessionCours));
        }

        return ResponseEntity.ok(sessionCoursDTOList);
    }



    @GetMapping("/{etudiantId}/cours")
    public List<CoursDTO> getCoursEtudiant(@PathVariable Long etudiantId) {
        // Rechercher l'étudiant dans la base de données
        Etudiant etudiant = etudiantRepository.findById(etudiantId).orElseThrow(() -> new RuntimeException("Étudiant introuvable"));

        // Récupérer la liste des cours de l'étudiant en utilisant une requête personnalisée
        List<CoursEtudiant> coursEtudiants = coursEtudiantRepository.findCoursByEtudiant(etudiant);

        // Créer une liste de DTO pour les cours
        List<CoursDTO> coursDTOList = coursEtudiants.stream()
                .map(coursEtudiant -> new CoursDTO(coursEtudiant.getCours()))
                .collect(Collectors.toList());

        return coursDTOList;
    }



}
