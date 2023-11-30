package com.example.uniamerica.pizzaria.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "<h4>Essa será a tela de Login!</h4>";
    }

}
