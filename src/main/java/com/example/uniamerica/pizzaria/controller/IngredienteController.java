package com.example.uniamerica.pizzaria.controller;

import com.example.uniamerica.pizzaria.dto.IngredienteDTO;
import com.example.uniamerica.pizzaria.service.IngredientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/ingrediente")
@CrossOrigin(origins="http://localhost:4200")

public class IngredienteController {
    @Autowired
    IngredientesService service;

    @GetMapping("/id/{id}")
    public ResponseEntity<IngredienteDTO> findById(@PathVariable("id") final Long id) {
        return ResponseEntity.ok().body(service.findByID(id));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<IngredienteDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<IngredienteDTO> post(@RequestBody @Validated IngredienteDTO ingredientes) {
        return ResponseEntity.ok(service.post(ingredientes));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<IngredienteDTO> put(@PathVariable("id") Long id, @RequestBody @Validated IngredienteDTO ingredientes) {
        return ResponseEntity.ok(service.update(id, ingredientes));
    }

    @DeleteMapping("/desativar/{id}")
    public ResponseEntity<IngredienteDTO> desativar(@PathVariable("id") final long id) {
        return ResponseEntity.ok(service.desativar(id));
    }
    @PutMapping("/desativar/{id}")
    public ResponseEntity<IngredienteDTO> ativar(@PathVariable("id") final long id) {
        return ResponseEntity.ok(service.ativar(id));
    }

}
