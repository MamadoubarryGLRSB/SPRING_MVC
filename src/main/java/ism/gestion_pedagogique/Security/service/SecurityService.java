package ism.gestion_pedagogique.Security.service;

import ism.gestion_pedagogique.Security.entities.AppRole;
import ism.gestion_pedagogique.Security.entities.AppUser;

public interface SecurityService {
    AppUser getUserByUsername(String username);
    AppUser saveUser(String nomComplet,String username,String password);
    AppRole saveRole(String  roleName);
    void addRoleToUser(String username,String  roleName);
    void removeRoleToUser(String username,String  roleName);
}
