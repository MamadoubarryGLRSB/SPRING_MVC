package ism.gestion_pedagogique.api.controller;

import ism.gestion_pedagogique.api.dto.CoursEtudiantDTO;
import ism.gestion_pedagogique.entities.CoursEtudiant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
@RestController

@RequestMapping("/api/etudiant_cours")
public class CoursEtudiantController {

    private final EntityManager entityManager;

    public CoursEtudiantController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @GetMapping
    public ResponseEntity<List<CoursEtudiantDTO>> getAllCoursEtudiant() {
        try {
            String query = "SELECT new ism.gestion_pedagogique.api.dto.CoursEtudiantDTO(e.id, e.cours, e.etu) " +
                    "FROM CoursEtudiant e";
            TypedQuery<CoursEtudiantDTO> typedQuery = entityManager.createQuery(query, CoursEtudiantDTO.class);
            List<CoursEtudiantDTO> resultList = typedQuery.getResultList();
            return ResponseEntity.ok(resultList);
        } catch (Exception e) {
            // Gérer l'exception ici
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}