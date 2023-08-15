package com.example.uniamerica.pizzaria.Controller;

import com.example.uniamerica.pizzaria.DTO.SaborDTO;
import com.example.uniamerica.pizzaria.Repository.SaborRepository;
import com.example.uniamerica.pizzaria.Service.SaborService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/sabores")
public class SaborController {
    @Autowired
    SaborRepository repository;
    @Autowired
    SaborService service;

    @GetMapping("/id")
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
    public ResponseEntity<?>editar(@RequestBody SaborDTO sabor, @RequestParam("id")Long id){
        try {
            return ResponseEntity.ok(service.update(sabor,id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
