package ism.gestion_pedagogique.Security.repositories;

import ism.gestion_pedagogique.Security.entities.AppRole;
import ism.gestion_pedagogique.Security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findByRoleName(String roleName);


}
