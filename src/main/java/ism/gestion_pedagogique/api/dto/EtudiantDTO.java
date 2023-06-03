package ism.gestion_pedagogique.api.dto;

import ism.gestion_pedagogique.Security.entities.AppRole;
import ism.gestion_pedagogique.entities.Etudiant;
import ism.gestion_pedagogique.entities.Professeur;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor

@Setter
@Getter
public class EtudiantDTO {

    private Long id;
    private String username;
    private String password;
    private String matricule;
    private String nomComplet;
    private List<AppRole> roles;

    public EtudiantDTO() {
        // Constructeur par défaut
    }

    public EtudiantDTO(Etudiant etudiant) {
        this.id = etudiant.getId();
        this.username = etudiant.getUsername();
        this.password = etudiant.getPassword();
        this.matricule = etudiant.getMatricule();
        this.nomComplet = etudiant.getNomComplet();
    }
}
