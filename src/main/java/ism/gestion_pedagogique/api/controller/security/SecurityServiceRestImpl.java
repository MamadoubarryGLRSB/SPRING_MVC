package ism.gestion_pedagogique.api.controller.security;

import ism.gestion_pedagogique.Security.entities.AppUser;
import ism.gestion_pedagogique.Security.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceRestImpl implements ISecurityService{
    @Autowired
    private AppUserRepository appUserRepository;
    @Override
    public AppUser getUserByLogin(String login) {
       AppUser user = (AppUser)appUserRepository.findByUsername(login);
       return user;
    }
}
