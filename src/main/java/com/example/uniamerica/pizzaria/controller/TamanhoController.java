package com.example.uniamerica.pizzaria.controller;

import com.example.uniamerica.pizzaria.dto.TamanhoDTO;
import com.example.uniamerica.pizzaria.service.TamanhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tamanho")
@CrossOrigin(origins="http://localhost:4200")
public class TamanhoController {
    @Autowired
    TamanhoService service;
    @GetMapping("/id/{id}")
    public ResponseEntity<TamanhoDTO> findById(@PathVariable("id") final Long id){
        return ResponseEntity.ok(service.findById(id));
    }
    @GetMapping("/todos")
    public ResponseEntity<List<TamanhoDTO>>getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<TamanhoDTO>cadastrar(@RequestBody @Validated TamanhoDTO tamanhoDTO){
        return ResponseEntity.ok(service.cadastrar(tamanhoDTO));
    }

    @PutMapping("/editar/[id]")
    public ResponseEntity<TamanhoDTO>editar(@PathVariable("id")final long id, @RequestBody @Validated TamanhoDTO tamanhoDTO){
        return ResponseEntity.ok(service.editar(id, tamanhoDTO));
    }
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String>deletar(@PathVariable("id") final long id){
        service.deletar(id);
        return ResponseEntity.ok(String.format("O tamanho com o ID %s foi deletado com sucesso.", id));
    }
}
