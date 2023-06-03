package ism.gestion_pedagogique.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "etudiant_sessions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Etudiant_Sessions_Cours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "sessionCours_id")
    private SessionCours sessionCours;

    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    private Etudiant etu;
}
