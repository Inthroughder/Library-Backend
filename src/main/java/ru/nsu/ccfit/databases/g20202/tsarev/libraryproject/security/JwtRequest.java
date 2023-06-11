package ru.nsu.ccfit.databases.g20202.tsarev.libraryproject.security;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtRequest {

    private String login;
    private String password;

}
