package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.security.Role;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private Set<Role> roles;

}
