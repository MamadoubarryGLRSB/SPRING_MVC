package ism.gestion_pedagogique.Security.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "role")
@DiscriminatorValue(value = "User")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected  Long id;

    public AppUser(Long id) {
        this.id = id;
    }



    @Column(nullable = false,unique = true)
    protected String username;




    @Column(nullable = false)
    protected String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id")
    )
    List<AppRole> roles;
}
