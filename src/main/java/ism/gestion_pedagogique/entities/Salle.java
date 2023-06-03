package ism.gestion_pedagogique.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "salles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numero;
    private  int nombrePlace;

    @OneToMany(mappedBy = "salle",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<SessionCours> sessionCours;
}

