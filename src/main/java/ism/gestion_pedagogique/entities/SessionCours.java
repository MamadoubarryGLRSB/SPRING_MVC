package ism.gestion_pedagogique.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sessionCours")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SessionCours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private int heureDebut;



    private int nbreHeures;

    private String etat;

    private  String etat_session;

    private  boolean demandeAnnulation;
    @ManyToOne
    @JoinColumn(name = "cours_id")
    @JsonManagedReference
    private Cours cours;


    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    @JsonManagedReference
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "classe_id")
    @JsonManagedReference
    private Classe classe;

    @ManyToOne
    @JoinColumn(name = "salle_id")
    @JsonManagedReference
    private Salle salle;

    @ManyToOne
    @JoinColumn(name = "module_id")
    @JsonManagedReference
    private Module module;



    @ManyToOne
    @JoinColumn(name = "professeur_id")
    @JsonManagedReference
    private Professeur professeur;






    @OneToMany(mappedBy = "sessionCours",fetch = FetchType.LAZY)
    private List<Etudiant_Sessions_Cours> etudiantSessionsCours;
}
