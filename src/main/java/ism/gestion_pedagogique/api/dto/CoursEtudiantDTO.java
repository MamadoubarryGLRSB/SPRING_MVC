package ism.gestion_pedagogique.api.dto;

import ism.gestion_pedagogique.entities.Cours;
import ism.gestion_pedagogique.entities.Etudiant;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CoursEtudiantDTO {
    private Long id;
    private Long coursId;
    private Long etudiantId;
    private String nomCours;

    public CoursEtudiantDTO(Long id, Cours cours, Etudiant etudiant) {
        this.id = id;
        this.coursId = cours.getId();
        this.etudiantId = etudiant.getId();
        this.nomCours = cours.getNom();
    }
}
