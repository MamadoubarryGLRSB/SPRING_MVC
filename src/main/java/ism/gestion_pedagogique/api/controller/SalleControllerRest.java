package ism.gestion_pedagogique.api.controller;

import ism.gestion_pedagogique.api.dto.SalleDTO;
import ism.gestion_pedagogique.entities.Salle;
import ism.gestion_pedagogique.repositories.SalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/salles")
public class SalleControllerRest {
    @Autowired
    private SalleRepository salleRepository;

    @GetMapping
    public ResponseEntity<List<SalleDTO>> getAllSalles() {
        List<Salle> salles = salleRepository.findAll();
        List<SalleDTO> salleDTOs = new ArrayList<>();
        for (Salle salle : salles) {
            salleDTOs.add(new SalleDTO(salle.getId(), salle.getNumero(), salle.getNombrePlace()));
        }
        return ResponseEntity.ok(salleDTOs);
    }

    @PostMapping("/ajouter-salle")
    public ResponseEntity<SalleDTO> ajouterSalle(@RequestBody SalleDTO salleDTO) {
        Salle salle = new Salle();
        salle.setNumero(salleDTO.getNumero());
        salle.setNombrePlace(salleDTO.getNombrePlace());
        salleRepository.save(salle);
        salleDTO.setId(salle.getId());
        return ResponseEntity.ok(salleDTO);
    }

    @PutMapping("/update-salle/{id}")
    public ResponseEntity<SalleDTO> updateSalle(@PathVariable("id") Long id, @RequestBody SalleDTO salleDTO) {
        Optional<Salle> optionalSalle = salleRepository.findById(id);
        if (optionalSalle.isPresent()) {
            Salle salle = optionalSalle.get();
            salle.setNumero(salleDTO.getNumero());
            salle.setNombrePlace(salleDTO.getNombrePlace());
            salleRepository.save(salle);
            salleDTO.setId(salle.getId());
            return ResponseEntity.ok(salleDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalleDTO> getSalle(@PathVariable("id") Long id) {
        Optional<Salle> optionalSalle = salleRepository.findById(id);
        if (optionalSalle.isPresent()) {
            Salle salle = optionalSalle.get();
            SalleDTO salleDTO = new SalleDTO();
            salleDTO.setId(salle.getId());
            salleDTO.setNumero(salle.getNumero());
            salleDTO.setNombrePlace(salle.getNombrePlace());
            return ResponseEntity.ok(salleDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete-salle/{id}")
    public ResponseEntity<Void> deleteSalle(@PathVariable("id") Long id) {
        Optional<Salle> optionalSalle = salleRepository.findById(id);
        if (optionalSalle.isPresent()) {
            salleRepository.delete(optionalSalle.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
