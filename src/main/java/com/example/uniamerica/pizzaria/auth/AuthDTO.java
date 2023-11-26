package com.example.uniamerica.pizzaria.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthDTO {
    private String access_token;
    private String realm_acess;
    private String roles;
}
