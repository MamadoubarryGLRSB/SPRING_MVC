package ism.gestion_pedagogique.Security.repositories;

import ism.gestion_pedagogique.Security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String username);
}
