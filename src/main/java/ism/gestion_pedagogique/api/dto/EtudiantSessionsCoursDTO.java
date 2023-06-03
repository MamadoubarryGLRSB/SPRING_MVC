package ism.gestion_pedagogique.api.dto;

import ism.gestion_pedagogique.entities.Etudiant;
import ism.gestion_pedagogique.entities.Etudiant_Sessions_Cours;
import ism.gestion_pedagogique.entities.SessionCours;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EtudiantSessionsCoursDTO {
    private Long id;
    private Long sessionCoursId;
    private Long etudiantId;
    private String nomCours;



    public EtudiantSessionsCoursDTO(Long id, SessionCours sessionCours, Etudiant etudiant) {
        this.id = id;
        this.sessionCoursId = sessionCours.getId();
        this.etudiantId = etudiant.getId();
        this.nomCours = sessionCours.getCours().getNom();

    }

    // Getters and setters
}
