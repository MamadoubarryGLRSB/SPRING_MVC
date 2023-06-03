package ism.gestion_pedagogique.api.dto;

import ism.gestion_pedagogique.entities.Classe;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClasseDTO {

    private Long id;
    private String libelle;
    private String niveau;
    private String filiere;


    public ClasseDTO(Classe classe) {
        this.id = classe.getId();
        this.libelle = classe.getLibelle();
        this.niveau = classe.getNiveau();
        this.filiere = classe.getFiliere();
    }
}