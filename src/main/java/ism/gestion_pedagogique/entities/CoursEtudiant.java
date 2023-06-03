package ism.gestion_pedagogique.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "etudiants_cours")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CoursEtudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "cours_id")
    private Cours cours;

    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    private Etudiant etu;
}
