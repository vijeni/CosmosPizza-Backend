package com.example.uniamerica.pizzaria.controller;

import com.example.uniamerica.pizzaria.dto.PessoaDTO;
import com.example.uniamerica.pizzaria.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {
    @Autowired
    PessoaService service;
    @GetMapping("/id/:id")
    public ResponseEntity<PessoaDTO>findById(@PathVariable("id") final Long id){
        return ResponseEntity.ok(service.findById(id));
    }
    @GetMapping("/todos")
    public ResponseEntity<List<PessoaDTO>>getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @PostMapping("/cadastrar")
    public ResponseEntity<PessoaDTO>cadastrar(@RequestBody @Validated PessoaDTO pessoa){
        return ResponseEntity.ok(service.post(pessoa));
    }
    @PutMapping("/editar/:id")
    public ResponseEntity<PessoaDTO>atualizar(@RequestBody @Validated PessoaDTO pessoa, @PathVariable long id){
        return ResponseEntity.ok(service.put(pessoa, id));
    }
    @DeleteMapping("/deletar/:id")
    public ResponseEntity<String>deletar(@PathVariable ("id") final long id){
        service.deletar(id);
        return ResponseEntity.ok(String.format("Pessoa com o id [%s] foi deletado com sucesso.", id));
    }
}
