package ism.gestion_pedagogique.api.dto;

import ism.gestion_pedagogique.entities.Cours;
import ism.gestion_pedagogique.entities.Semestre;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SemestreDTO {

    private Long id;
    private String libelle;
    private List<Long> coursIds;

    public SemestreDTO(Semestre semestre) {
        this.id = semestre.getId();
        this.libelle = semestre.getLibelle();
        this.coursIds = semestre.getCoursList().stream()
                .map(Cours::getId)
                .collect(Collectors.toList());
    }
}
