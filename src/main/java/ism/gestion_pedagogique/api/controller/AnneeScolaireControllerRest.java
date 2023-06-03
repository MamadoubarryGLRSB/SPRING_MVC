package ism.gestion_pedagogique.api.controller;

import ism.gestion_pedagogique.api.dto.AnneeScolaireDTO;
import ism.gestion_pedagogique.entities.AnneeScolaire;
import ism.gestion_pedagogique.repositories.AnneeScolaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/anneeScolaires")
public class AnneeScolaireControllerRest {

    @Autowired
    private AnneeScolaireRepository anneeScolaireRepository;



    @GetMapping
    public ResponseEntity<List<AnneeScolaireDTO>> getAllAnneeScolaires() {
        List<AnneeScolaire> anneeScolaires = anneeScolaireRepository.findAll();
        List<AnneeScolaireDTO> anneeScolaireDTOs = new ArrayList<>();
        for (AnneeScolaire anneeScolaire : anneeScolaires) {
            anneeScolaireDTOs.add(new AnneeScolaireDTO(anneeScolaire));
        }
        return ResponseEntity.ok(anneeScolaireDTOs);
    }


    @PostMapping("/ajouter-annee-scolaire")
    public ResponseEntity<AnneeScolaireDTO> ajouterAnneeScolaire(@RequestBody AnneeScolaireDTO anneeScolaireDTO) {
        AnneeScolaire anneeScolaire = new AnneeScolaire();
        anneeScolaire.setDateDebut(anneeScolaireDTO.getDateDebut());
        anneeScolaire.setDateFin(anneeScolaireDTO.getDateFin());
        // Vous pouvez également traiter la relation avec les planifications de classe ici, si nécessaire

        anneeScolaireRepository.save(anneeScolaire);
        anneeScolaireDTO.setId(anneeScolaire.getId());

        return ResponseEntity.ok(anneeScolaireDTO);
    }


    @PostMapping("/enregistrer-annee-scolaire")
    public ResponseEntity<AnneeScolaireDTO> enregistrerAnneeScolaire(@RequestBody AnneeScolaireDTO anneeScolaireDTO) {
        AnneeScolaire anneeScolaire = new AnneeScolaire();
        anneeScolaire.setDateDebut(anneeScolaireDTO.getDateDebut());
        anneeScolaire.setDateFin(anneeScolaireDTO.getDateFin());
        anneeScolaireRepository.save(anneeScolaire);
        anneeScolaireDTO.setId(anneeScolaire.getId());
        return ResponseEntity.ok(anneeScolaireDTO);
    }





}
