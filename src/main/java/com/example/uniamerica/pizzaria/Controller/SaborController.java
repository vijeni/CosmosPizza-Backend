package com.example.uniamerica.pizzaria.Controller;

import com.example.uniamerica.pizzaria.DTO.SaborDTO;
import com.example.uniamerica.pizzaria.Repository.SaborRepository;
import com.example.uniamerica.pizzaria.Service.SaborService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/sabor")
public class SaborController {
    @Autowired
    SaborRepository repository;
    @Autowired
    SaborService service;

    @GetMapping
    public ResponseEntity<?>findById(@RequestParam("id") final long id){
        try{
           return ResponseEntity.ok(repository.findById(id).orElse(null));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?>cadastrar(@RequestBody SaborDTO sabor){
        try{
            return ResponseEntity.ok(service.cadastrar(sabor));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<?>editar(@RequestBody SaborDTO sabor, @RequestParam("id")final long id){
        try {
            return ResponseEntity.ok(service.update(sabor,id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("deletar")
    public ResponseEntity<?>deletar(@RequestParam("id") final long id){
        try{
            service.delete(id);
            return ResponseEntity.ok(String.format("O sabor com o id [%s] foi deletado com sucesso.", id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
