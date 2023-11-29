package com.example.uniamerica.pizzaria.controller;

import com.example.uniamerica.pizzaria.dto.SaborDTO;
import com.example.uniamerica.pizzaria.service.SaborService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/sabor")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class SaborController {
    @Autowired
    SaborService service;
    @GetMapping("/id/{id}")
    @PreAuthorize("hasAnyAuthority('FUNCIONARIO', 'ADMIN')")
    public ResponseEntity<SaborDTO>findById(@PathVariable("id") final long id){
        return ResponseEntity.ok(service.findById(id));
    }
    @GetMapping("/todos")
    @PreAuthorize("hasAnyAuthority('FUNCIONARIO', 'ADMIN')")
    public ResponseEntity<List<SaborDTO>>getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/cadastrar")
        @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<SaborDTO>cadastrar(@RequestBody @Validated SaborDTO sabor){
        return ResponseEntity.ok(service.cadastrar(sabor));
    }

    @PutMapping("/editar/{id}")
        @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<SaborDTO>editar(@RequestBody @Validated SaborDTO sabor, @PathVariable("id")final long id){
        return ResponseEntity.ok(service.update(sabor,id));
    }
    @DeleteMapping("/desativar/{id}")
        @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<SaborDTO> desativar(@PathVariable("id") final long id){
        return ResponseEntity.ok(service.desativar(id));
    }
}
