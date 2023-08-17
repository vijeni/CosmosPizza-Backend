package com.example.uniamerica.pizzaria.Controller;

import com.example.uniamerica.pizzaria.DTO.PizzaDTO;
import com.example.uniamerica.pizzaria.Service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
    @Autowired
    PizzaService service;
    @GetMapping
    public ResponseEntity<?> findById(@RequestParam Long id){
        try{
            return ResponseEntity.ok(service.findById(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/todos")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.ok(service.getAll());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody PizzaDTO pizza){
        try{
            return ResponseEntity.ok(service.cadastrar(pizza));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<?> editar(@RequestParam Long id, @RequestBody PizzaDTO pizza){
        try{
            return ResponseEntity.ok(service.editar(id, pizza));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?> deletar(@RequestParam Long id){
        try{
            service.deletar(id);
            return ResponseEntity.ok(String.format("Pizza com ID %s foi deletado com sucesso!", id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
