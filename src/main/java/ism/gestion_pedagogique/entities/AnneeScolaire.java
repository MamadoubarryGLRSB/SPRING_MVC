package ism.gestion_pedagogique.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "anneeScolaire")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class AnneeScolaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateDebut;
    private LocalDate dateFin;

    @OneToMany(mappedBy = "anneeScolaire",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<PlanificationClasse> planificationClasses;


}
