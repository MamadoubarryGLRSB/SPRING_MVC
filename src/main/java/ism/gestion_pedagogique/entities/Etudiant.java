package ism.gestion_pedagogique.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ism.gestion_pedagogique.Security.entities.AppUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "etudiants")
@Data
@DiscriminatorValue(value = "Etudiant")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Etudiant extends AppUser {

    @OneToMany(mappedBy = "etudiant",fetch = FetchType.LAZY)
    private List<Inscription> inscriptions;

    private  String matricule;

    private  String nomComplet;

    @OneToMany(mappedBy = "etu",fetch = FetchType.EAGER)
    private List<Etudiant_Sessions_Cours> etudiantSessionsCours;

    @OneToMany(mappedBy = "etudiant",fetch = FetchType.LAZY)
    private List<ClasseEtudiant> classeEtudiants;

    @OneToMany(mappedBy = "etudiant",fetch = FetchType.LAZY)
    private List<Professeur_Etudiant> professeurClasses;

    @ManyToOne
    @JoinColumn(name = "absence_id")
    private Absence absence;

    @OneToMany(mappedBy = "etudiant",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<SessionCours> sessionCours;
    @ManyToOne
    @JoinColumn(name = "emargement_id")
    private Emargement emargement;



    @OneToMany(mappedBy = "etu",fetch = FetchType.EAGER)
    private List<CoursEtudiant> coursEtudiants;
}
