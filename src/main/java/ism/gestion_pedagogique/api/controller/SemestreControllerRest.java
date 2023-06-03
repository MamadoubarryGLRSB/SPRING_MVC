package ism.gestion_pedagogique.api.controller;

import ism.gestion_pedagogique.api.dto.SemestreDTO;
import ism.gestion_pedagogique.entities.Semestre;
import ism.gestion_pedagogique.repositories.SemestreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/semestres")
public class SemestreControllerRest {
    @Autowired
    private SemestreRepository semestreRepository;

    @GetMapping
    public ResponseEntity<List<SemestreDTO>> getAllSemestres() {
        List<Semestre> semestres = semestreRepository.findAll();
        List<SemestreDTO> semestreDTOs = semestres.stream()
                .map(SemestreDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(semestreDTOs);
    }

    @PostMapping("/ajouter-semestre")
    public ResponseEntity<SemestreDTO> ajouterSemestre(@RequestBody SemestreDTO semestreDTO) {
        Semestre semestre = new Semestre();
        semestre.setLibelle(semestreDTO.getLibelle());
        semestreRepository.save(semestre);
        semestreDTO.setId(semestre.getId());
        return ResponseEntity.ok(semestreDTO);
    }

    @PostMapping("/enregistrer-semestre")
    public ResponseEntity<SemestreDTO> enregistrerSemestre(@RequestBody SemestreDTO semestreDTO) {
        Semestre semestre = new Semestre();
        semestre.setLibelle(semestreDTO.getLibelle());
        semestreRepository.save(semestre);
        semestreDTO.setId(semestre.getId());
        return ResponseEntity.ok(semestreDTO);
    }

    @PutMapping("/update-semestre/{id}")
    public ResponseEntity<SemestreDTO> updateSemestre(@PathVariable("id") Long id, @RequestBody SemestreDTO semestreDTO) {
        Optional<Semestre> optionalSemestre = semestreRepository.findById(id);
        if (optionalSemestre.isPresent()) {
            Semestre semestre = optionalSemestre.get();
            semestre.setLibelle(semestreDTO.getLibelle());
            semestreRepository.save(semestre);
            semestreDTO.setId(semestre.getId());
            return ResponseEntity.ok(semestreDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SemestreDTO> getSemestre(@PathVariable("id") Long id) {
        Optional<Semestre> optionalSemestre = semestreRepository.findById(id);
        if (optionalSemestre.isPresent()) {
            Semestre semestre = optionalSemestre.get();
            SemestreDTO semestreDTO = new SemestreDTO(semestre);
            return ResponseEntity.ok(semestreDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete-semestre/{id}")
    public ResponseEntity<Void> deleteSemestre(@PathVariable("id") Long id) {
        Optional<Semestre> optionalSemestre = semestreRepository.findById(id);
        if (optionalSemestre.isPresent()) {
            semestreRepository.delete(optionalSemestre.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
