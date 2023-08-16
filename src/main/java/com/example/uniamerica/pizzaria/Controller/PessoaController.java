package com.example.uniamerica.pizzaria.Controller;

import com.example.uniamerica.pizzaria.DTO.PessoaDTO;
import com.example.uniamerica.pizzaria.Repository.PessoaRepository;
import com.example.uniamerica.pizzaria.Service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {
    @Autowired
    PessoaRepository repository;
    @Autowired
    PessoaService service;
    @GetMapping
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
    @PutMapping("/editar")
    public ResponseEntity<?>atualizar(@RequestBody PessoaDTO pessoa, @RequestParam long id){
        try{
            return ResponseEntity.ok(service.put(pessoa));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?>deletar(@RequestParam ("id") final long id){
        try {
            service.deletar(id);
            return ResponseEntity.ok(String.format("Pessoa com o id [%s] foi deletado com sucesso.", id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
