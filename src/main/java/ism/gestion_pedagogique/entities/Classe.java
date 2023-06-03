package ism.gestion_pedagogique.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "classes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class Classe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String libelle;
    private  String niveau;
    private  String filiere;

    @OneToMany(mappedBy = "classe",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<SessionCours> sessionCours;


    @OneToMany(mappedBy = "classe",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<ClasseEtudiant> classeEtudiants;

    @OneToMany(mappedBy = "classe",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Classe_Prof> classeProfs;

    @OneToMany(mappedBy = "classe",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<PlanificationClasse> planificationClasses;



}
