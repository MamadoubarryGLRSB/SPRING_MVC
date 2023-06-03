package ism.gestion_pedagogique.api.controller;

import ism.gestion_pedagogique.api.dto.ClasseDTO;
import ism.gestion_pedagogique.entities.Classe;
import ism.gestion_pedagogique.entities.Professeur;
import ism.gestion_pedagogique.entities.Salle;
import ism.gestion_pedagogique.repositories.ClasseRepository;
import ism.gestion_pedagogique.repositories.ProfesseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/classes")
public class ClasseControllerRest{

    @Autowired
    private ClasseRepository classeRepository;

    @GetMapping
    public ResponseEntity<List<ClasseDTO>> getAllClasses() {
        List<Classe> classes = classeRepository.findAll();
        List<ClasseDTO> classeDTOs = new ArrayList<>();
        for (Classe classe : classes) {
            classeDTOs.add(new ClasseDTO(classe));
        }
        return ResponseEntity.ok(classeDTOs);
    }


    @PostMapping("/ajouter-classe")
    public ResponseEntity<ClasseDTO> ajouterClasse(@RequestBody ClasseDTO classeDTO) {
        Classe classe = new Classe();
        classe.setLibelle(classeDTO.getLibelle());
        classe.setNiveau(classeDTO.getNiveau());
        classe.setFiliere(classeDTO.getFiliere());
        classeRepository.save(classe);
        classeDTO.setId(classe.getId());
        return ResponseEntity.ok(classeDTO);
    }


    @PostMapping("/enregistrer-classe")
    public ResponseEntity<ClasseDTO> enregistrerClasse(@RequestBody ClasseDTO classeDTO) {
        Classe classe = new Classe();
        classe.setLibelle(classeDTO.getLibelle());
        classe.setNiveau(classeDTO.getNiveau());
        classe.setFiliere(classeDTO.getFiliere());
        classeRepository.save(classe);
        classeDTO.setId(classe.getId());
        return ResponseEntity.ok(classeDTO);
    }

    @PutMapping("/update-classe/{id}")
    public ResponseEntity<ClasseDTO> updateClasse(@PathVariable("id") Long id, @RequestBody ClasseDTO classeDTO) {
        Optional<Classe> optionalClasse = classeRepository.findById(id);
        if (optionalClasse.isPresent()) {
            Classe classe = optionalClasse.get();
            classe.setLibelle(classeDTO.getLibelle());
            classe.setNiveau(classeDTO.getNiveau());
            classe.setFiliere(classeDTO.getFiliere());
            classeRepository.save(classe);
            classeDTO.setId(classe.getId());
            return ResponseEntity.ok(classeDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClasseDTO> getClasse(@PathVariable("id") Long id) {
        Optional<Classe> optionalClasse = classeRepository.findById(id);
        if (optionalClasse.isPresent()) {
            Classe classe = optionalClasse.get();
            ClasseDTO classeDTO = new ClasseDTO();
            classeDTO.setId(classe.getId());
            classeDTO.setLibelle(classe.getLibelle());
            classeDTO.setNiveau(classe.getNiveau());
            classeDTO.setFiliere(classe.getFiliere());
            return ResponseEntity.ok(classeDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/delete-classe/{id}")
    public ResponseEntity<Void> deleteClasse(@PathVariable("id") Long id) {
        Optional<Classe> optionalClasse = classeRepository.findById(id);
        if (optionalClasse.isPresent()) {
            classeRepository.delete(optionalClasse.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
