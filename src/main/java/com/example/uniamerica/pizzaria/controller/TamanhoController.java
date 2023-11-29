package com.example.uniamerica.pizzaria.controller;

import com.example.uniamerica.pizzaria.dto.TamanhoDTO;
import com.example.uniamerica.pizzaria.service.TamanhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tamanho")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class TamanhoController {
    @Autowired
    TamanhoService service;
    @GetMapping("/id/{id}")
    @PreAuthorize("hasAnyAuthority('FUNCIONARIO', 'ADMIN')")
    public ResponseEntity<TamanhoDTO> findById(@PathVariable("id") final Long id){
        return ResponseEntity.ok(service.findById(id));
    }
    @GetMapping("/todos")
    @PreAuthorize("hasAnyAuthority('FUNCIONARIO', 'ADMIN')")
    public ResponseEntity<List<TamanhoDTO>>getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/cadastrar")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<TamanhoDTO>cadastrar(@RequestBody @Validated TamanhoDTO tamanhoDTO){
        return ResponseEntity.ok(service.cadastrar(tamanhoDTO));
    }

    @PutMapping("/editar/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<TamanhoDTO>editar(@PathVariable("id")final long id, @RequestBody @Validated TamanhoDTO tamanhoDTO){
        return ResponseEntity.ok(service.editar(id, tamanhoDTO));
    }
    @DeleteMapping("/desativar/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<TamanhoDTO> desativar(@PathVariable("id") final long id){
        return ResponseEntity.ok(service.desativar(id));}
    @DeleteMapping("/ativar/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<TamanhoDTO> ativar(@PathVariable("id") final long id){
        return ResponseEntity.ok(service.ativar(id));
    }
}
