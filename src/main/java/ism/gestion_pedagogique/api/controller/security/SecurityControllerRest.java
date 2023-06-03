package ism.gestion_pedagogique.api.controller.security;

import ism.gestion_pedagogique.Security.entities.AppUser;
import ism.gestion_pedagogique.api.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/security")
public class SecurityControllerRest {
    @Autowired
    ISecurityService service;
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<UserDto> connection(@RequestBody UserConnectDto userConnect){
        AppUser client=service.getUserByLogin(userConnect.username);
        if(client==null) throw new RuntimeException(
                "Login ou Mot de Passe Incorrect");
        return  new ResponseEntity<>(new UserDto(client), HttpStatus.CREATED);
    }
}
class UserConnectDto{
    public String   username;
    public String   password;
}