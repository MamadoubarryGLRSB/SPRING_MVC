package ism.gestion_pedagogique.api.dto;

import ism.gestion_pedagogique.entities.AnneeScolaire;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AnneeScolaireDTO {

    private Long id;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    public AnneeScolaireDTO(AnneeScolaire anneeScolaire) {
        this.id = anneeScolaire.getId();
        this.dateDebut = anneeScolaire.getDateDebut();
        this.dateFin = anneeScolaire.getDateFin();
    }
}
