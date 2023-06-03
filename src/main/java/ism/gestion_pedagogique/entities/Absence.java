package ism.gestion_pedagogique.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "absences")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private  String motif;
    private  Boolean justification ;

    @OneToMany(mappedBy = "absence",fetch = FetchType.LAZY)
    private List<Etudiant> etudiants;
}
