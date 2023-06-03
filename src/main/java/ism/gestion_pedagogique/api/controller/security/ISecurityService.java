package ism.gestion_pedagogique.api.controller.security;

import ism.gestion_pedagogique.Security.entities.AppUser;

public interface ISecurityService {
    AppUser getUserByLogin(String login);
}
