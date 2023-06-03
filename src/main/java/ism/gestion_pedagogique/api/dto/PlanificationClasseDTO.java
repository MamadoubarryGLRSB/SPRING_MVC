package ism.gestion_pedagogique.api.dto;

import ism.gestion_pedagogique.entities.PlanificationClasse;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PlanificationClasseDTO {

    private Long id;
    private String libelle;
    private String niveau;
    private Long classeId;
    private Long anneeScolaireId;


    public PlanificationClasseDTO(PlanificationClasse planificationClasse) {
        this.id = planificationClasse.getId();
        this.libelle = planificationClasse.getLibelle();
        this.niveau = planificationClasse.getNiveau();
        this.classeId = planificationClasse.getClasse().getId();
        this.anneeScolaireId = planificationClasse.getAnneeScolaire().getId();
    }
}
