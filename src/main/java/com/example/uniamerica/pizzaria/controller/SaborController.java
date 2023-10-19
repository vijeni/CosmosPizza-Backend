package com.example.uniamerica.pizzaria.controller;

import com.example.uniamerica.pizzaria.dto.SaborDTO;
import com.example.uniamerica.pizzaria.service.SaborService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/sabor")
public class SaborController {
    @Autowired
    SaborService service;
    @GetMapping("/:id")
    public ResponseEntity<SaborDTO>findById(@PathVariable("id") final long id){
        return ResponseEntity.ok(service.findById(id));
    }
    @GetMapping("/todos")
    public ResponseEntity<List<SaborDTO>>getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<SaborDTO>cadastrar(@RequestBody @Validated SaborDTO sabor){
        return ResponseEntity.ok(service.cadastrar(sabor));
    }

    @PutMapping("/editar/:id")
    public ResponseEntity<SaborDTO>editar(@RequestBody @Validated SaborDTO sabor, @PathVariable("id")final long id){
        return ResponseEntity.ok(service.update(sabor,id));
    }
    @DeleteMapping("/deletar/:id")
    public ResponseEntity<String>deletar(@PathVariable("id") final long id){
        service.delete(id);
        return ResponseEntity.ok(String.format("O sabor com o id [%s] foi deletado com sucesso.", id));
    }
}
