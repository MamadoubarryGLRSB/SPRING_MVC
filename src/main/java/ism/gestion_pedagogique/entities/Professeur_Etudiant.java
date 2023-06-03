package ism.gestion_pedagogique.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "prof_classes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Professeur_Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "professeur_id")
    private Professeur professeur;
}
