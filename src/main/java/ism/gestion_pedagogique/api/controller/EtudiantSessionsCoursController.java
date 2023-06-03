package ism.gestion_pedagogique.api.controller;


import ism.gestion_pedagogique.api.dto.EtudiantSessionsCoursDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/etudiant_sessionCours")
public class EtudiantSessionsCoursController {

    private final EntityManager entityManager;

    public EtudiantSessionsCoursController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @GetMapping
    public List<EtudiantSessionsCoursDTO> getAllEtudiantSessionsCours() {
        String query = "SELECT new ism.gestion_pedagogique.api.dto.EtudiantSessionsCoursDTO(e.id, e.sessionCours, e.etu) " +
                "FROM Etudiant_Sessions_Cours e";
        TypedQuery<EtudiantSessionsCoursDTO> typedQuery = entityManager.createQuery(query, EtudiantSessionsCoursDTO.class);
        return typedQuery.getResultList();
    }
}
