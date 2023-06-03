package ism.gestion_pedagogique.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "cours")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Cours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int nbreHeures;

    private String raisonAnnulation;

    private String etat;
    private  String nom;

    private boolean annule = false;
    @ManyToOne
    @JoinColumn(name = "semestre_id")
    @JsonManagedReference
    private Semestre semestre;


    @OneToMany(mappedBy = "cours",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<SessionCours> sessionCours;

    @OneToMany(mappedBy = "cour",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Inscription> inscriptions;

    @OneToMany(mappedBy = "cours",fetch = FetchType.EAGER)
    @JsonBackReference
    private List<CoursEtudiant> coursEtudiants;
}
