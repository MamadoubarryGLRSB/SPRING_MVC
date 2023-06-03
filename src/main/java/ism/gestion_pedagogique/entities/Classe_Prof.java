package ism.gestion_pedagogique.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "classe_profs")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Classe_Prof {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "professeur_id")
    private Professeur prof;

    @ManyToOne
    @JoinColumn(name = "classe_id")
    private Classe classe;

}
