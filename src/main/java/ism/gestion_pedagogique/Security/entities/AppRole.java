package ism.gestion_pedagogique.Security.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "roles")

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected  Long id;


    public AppRole(String roleName) {
        this.roleName = roleName;
    }

    @Column(nullable = false,unique = true)
    protected  String roleName;

    @ManyToMany(mappedBy = "roles")
    @JsonBackReference
    List<AppUser> users;
}
