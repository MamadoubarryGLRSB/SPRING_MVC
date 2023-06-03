package ism.gestion_pedagogique.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "emargements")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Emargement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;

    @OneToMany(mappedBy = "emargement",fetch = FetchType.LAZY)
    private List<Etudiant> etudiants;
}
