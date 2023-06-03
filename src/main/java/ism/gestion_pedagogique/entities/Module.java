package ism.gestion_pedagogique.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;



@Entity
@Table(name = "moduls")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String libelle;



    public Module(Long id) {
        this.id = id;
    }


    @OneToMany(mappedBy = "module",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<SessionCours> sessionCours;

}
