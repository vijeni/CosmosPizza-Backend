package com.example.uniamerica.pizzaria.Controller;

import com.example.uniamerica.pizzaria.DTO.PessoaDTO;
import com.example.uniamerica.pizzaria.Repository.PessoaRepository;
import com.example.uniamerica.pizzaria.Service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {
    @Autowired
    PessoaRepository repository;
    @Autowired
    PessoaService service;
    @GetMapping("/id")
    public ResponseEntity<?>findById(@RequestParam("id") final Long id){
        try{
            return ResponseEntity.ok(repository.findById(id).orElse(null));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/cadastrar")
    public ResponseEntity<?>cadastrar(@RequestBody PessoaDTO pessoa){
        try{
            return ResponseEntity.ok(service.post(pessoa));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/atualizar")
    public ResponseEntity<?>atualizar(@RequestBody PessoaDTO pessoa, @RequestParam long id){
        try{
            return ResponseEntity.ok(service.put(pessoa));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
