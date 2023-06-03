package ism.gestion_pedagogique.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ism.gestion_pedagogique.Security.entities.AppUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "professeurs")
@Data
@DiscriminatorValue(value = "Professeur")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Professeur extends AppUser {

    private  String specialites;
    private  String grades;


    private  String nomComplet;

    public Professeur(Long id) {
        super(id);
    }


    @OneToMany(mappedBy = "professeur",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<SessionCours> sessionCours;

    @OneToMany(mappedBy = "professeur",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Professeur_Etudiant> professeurClasses;

    @OneToMany(mappedBy = "prof",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Classe_Prof> classeProfs;
}
