package ism.gestion_pedagogique.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "classe_etudiants")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClasseEtudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "classes_id")
    private Classe classe;

    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;
}
