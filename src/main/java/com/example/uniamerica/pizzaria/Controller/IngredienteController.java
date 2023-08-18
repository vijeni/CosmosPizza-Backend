package com.example.uniamerica.pizzaria.Controller;

import com.example.uniamerica.pizzaria.DTO.IngredienteDTO;
import com.example.uniamerica.pizzaria.Repository.IngredienteRepository;
import com.example.uniamerica.pizzaria.Service.IngredientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/ingrediente")
public class IngredienteController {
    @Autowired
    IngredienteRepository repository;
    @Autowired
    IngredientesService service;


    @GetMapping
    public ResponseEntity<?>findById(@RequestParam("id") final Long id){
        try{
            return ResponseEntity.ok().body(repository.findById(id).orElse(null));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<?>getAll(){
        try {
            return ResponseEntity.ok(service.getAll());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?>post(@RequestBody @Validated IngredienteDTO ingredientes){
        try{
            return ResponseEntity.ok(service.post(ingredientes));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/editar")
    public ResponseEntity<?>put(@RequestParam("id") Long id, @RequestBody @Validated IngredienteDTO ingredientes){
        try{
            return ResponseEntity.ok(service.update(id, ingredientes));
        }catch (Exception e){
           return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?>deletar(@RequestParam ("id") final long id){
        try{
            service.delete(id);
          return ResponseEntity.ok(String.format("O ingrediente com o id [%s] foi deletado com sucesso.", id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
