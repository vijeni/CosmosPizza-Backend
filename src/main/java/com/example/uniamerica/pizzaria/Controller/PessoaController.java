package com.example.uniamerica.pizzaria.Controller;

import com.example.uniamerica.pizzaria.Repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {
    @Autowired
    PessoaRepository repository;
    @GetMapping("/id")
    public ResponseEntity<?>findById(@RequestParam("id") final Long id){
        try{
            return ResponseEntity.ok(repository.findById(id).orElse(null));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




}
