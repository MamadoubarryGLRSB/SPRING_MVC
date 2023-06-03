package ism.gestion_pedagogique.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "semestres")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Semestre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;


    @OneToMany(mappedBy = "semestre",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Cours> coursList;
}
