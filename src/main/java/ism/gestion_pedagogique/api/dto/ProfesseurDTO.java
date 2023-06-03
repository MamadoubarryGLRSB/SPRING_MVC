package ism.gestion_pedagogique.api.dto;

import ism.gestion_pedagogique.Security.entities.AppRole;
import ism.gestion_pedagogique.entities.Professeur;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor

@Setter
@Getter
public class ProfesseurDTO {
    private Long id;
    private String username;
    private String password;
    private String specialites;
    private String grades;
    private String nomComplet;
    private  List<AppRole> roles;



    public ProfesseurDTO(Professeur professeur) {
        this.id = professeur.getId();
        this.username = professeur.getUsername();
        this.password = professeur.getPassword();
        this.specialites = professeur.getSpecialites();
        this.grades = professeur.getGrades();
        this.nomComplet = professeur.getNomComplet();
    }
}

