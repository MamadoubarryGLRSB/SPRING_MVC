package ism.gestion_pedagogique.api.dto;

import ism.gestion_pedagogique.entities.Module;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ModuleDTO {

    private Long id;
    private String libelle;

    public ModuleDTO(Module module) {
        this.id = module.getId();
        this.libelle = module.getLibelle();
    }
}
