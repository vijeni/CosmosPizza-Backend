package com.example.uniamerica.pizzaria.controller;

import com.example.uniamerica.pizzaria.dto.TamanhoDTO;
import com.example.uniamerica.pizzaria.service.TamanhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tamanho")
public class TamanhoController {
    @Autowired
    TamanhoService service;
    @GetMapping
    public ResponseEntity<TamanhoDTO> findById(@RequestParam("id") final Long id){
        return ResponseEntity.ok(service.findById(id));
    }
    @GetMapping("/todos")
    public ResponseEntity<List<TamanhoDTO>>getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<TamanhoDTO>cadastrar(@RequestBody @Validated TamanhoDTO tamanhoDTO){
        return ResponseEntity.ok(service.cadastrar(tamanhoDTO));
    }

    @PutMapping("/editar")
    public ResponseEntity<TamanhoDTO>editar(@RequestParam("id")final long id, @RequestBody @Validated TamanhoDTO tamanhoDTO){
        return ResponseEntity.ok(service.editar(id, tamanhoDTO));
    }
    @DeleteMapping("deletar")
    public ResponseEntity<String>deletar(@RequestParam("id") final long id){
        service.deletar(id);
        return ResponseEntity.ok(String.format("O tamanho com o ID %s foi deletado com sucesso.", id));
    }
}
