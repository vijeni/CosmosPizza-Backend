package com.example.uniamerica.pizzaria.controller;

import com.example.uniamerica.pizzaria.dto.IngredienteDTO;
import com.example.uniamerica.pizzaria.service.IngredientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/ingrediente")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class IngredienteController {
    @Autowired
    IngredientesService service;

    @GetMapping("/id/{id}")
    @PreAuthorize("hasAnyAuthority('FUNCIONARIO', 'ADMIN')")
    public ResponseEntity<IngredienteDTO> findById(@PathVariable("id") final Long id) {
        return ResponseEntity.ok().body(service.findByID(id));
    }

    @GetMapping("/todos")
    @PreAuthorize("hasAnyAuthority('FUNCIONARIO', 'ADMIN')")
    public ResponseEntity<List<IngredienteDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/cadastrar")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<IngredienteDTO> post(@RequestBody @Validated IngredienteDTO ingredientes) {
        return ResponseEntity.ok(service.post(ingredientes));
    }

    @PutMapping("/editar/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<IngredienteDTO> put(@PathVariable("id") Long id, @RequestBody @Validated IngredienteDTO ingredientes) {
        return ResponseEntity.ok(service.update(id, ingredientes));
    }

    @DeleteMapping("/desativar/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<IngredienteDTO> desativar(@PathVariable("id") final long id) {
        return ResponseEntity.ok(service.desativar(id));
    }
    @PutMapping("/ativar/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<IngredienteDTO> ativar(@PathVariable("id") final long id) {
        return ResponseEntity.ok(service.ativar(id));
    }

}
