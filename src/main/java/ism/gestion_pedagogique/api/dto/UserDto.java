package ism.gestion_pedagogique.api.dto;

import ism.gestion_pedagogique.Security.entities.AppUser;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDto {

    private Long id;
    private String username;
    private String password;

    private List<String> roles;


    public UserDto(AppUser user) {
       id=user.getId();
       username=user.getUsername();
        roles=user.getRoles()
                .stream()
                .map(role->role.getRoleName())
                .collect(Collectors.toList());
    }
}
