package com.example.uniamerica.pizzaria.Controller;

import com.example.uniamerica.pizzaria.DTO.EnderecoDTO;
import com.example.uniamerica.pizzaria.DTO.IngredienteDTO;
import com.example.uniamerica.pizzaria.Repository.IngredienteRepository;
import com.example.uniamerica.pizzaria.Service.IngredientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/ingrediente")
public class IngredienteController {
    @Autowired
    IngredientesService service;

    @GetMapping
    public ResponseEntity<IngredienteDTO> findById(@RequestParam("id") final Long id) {
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

    @PutMapping("/editar")
    public ResponseEntity<IngredienteDTO> put(@RequestParam("id") Long id, @RequestBody @Validated IngredienteDTO ingredientes) {
        return ResponseEntity.ok(service.update(id, ingredientes));
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<String> deletar(@RequestParam("id") final long id) {
        service.delete(id);
        return ResponseEntity.ok(String.format("O ingrediente com o ID [%s] foi deletado com sucesso.", id));
    }

}
