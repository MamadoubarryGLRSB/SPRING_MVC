package ism.gestion_pedagogique.api.dto;

import ism.gestion_pedagogique.entities.Cours;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CoursDTO {
    private Long id;
    private int nbreHeures;
    private String raisonAnnulation;
    private String etat;
    private String nom;
    private boolean annule;
    private Long semestreId;

    public CoursDTO(Cours cours) {
        this.id = cours.getId();
        this.nbreHeures = cours.getNbreHeures();
        this.raisonAnnulation = cours.getRaisonAnnulation();
        this.etat = cours.getEtat();
        this.nom = cours.getNom();
        this.annule = cours.isAnnule();
        this.semestreId = cours.getSemestre().getId();
    }
}
