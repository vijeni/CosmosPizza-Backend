package com.example.uniamerica.pizzaria.Controller;

import com.example.uniamerica.pizzaria.DTO.ProdutoDTO;
import com.example.uniamerica.pizzaria.Service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    @Autowired
    ProdutoService service;
    @GetMapping
    public ResponseEntity<?> findById(@RequestParam Long id){
        try{
            return ResponseEntity.ok(service.findById(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody ProdutoDTO produto){
        try{
            return ResponseEntity.ok(service.cadastrar(produto));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<?> editar(@RequestParam Long id, @RequestBody ProdutoDTO produto){
        try{
            return ResponseEntity.ok(service.editar(id, produto));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
