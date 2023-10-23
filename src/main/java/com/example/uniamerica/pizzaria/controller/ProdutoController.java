package com.example.uniamerica.pizzaria.controller;

import com.example.uniamerica.pizzaria.dto.ProdutoDTO;
import com.example.uniamerica.pizzaria.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
@CrossOrigin(origins="http://localhost:4200")
public class ProdutoController {
    @Autowired
    ProdutoService service;
    @GetMapping("/id/{id}")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }
    @GetMapping("/todos")
    public ResponseEntity<List<ProdutoDTO>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<List<ProdutoDTO>> getAllByNome(@PathVariable String descricao){
        return ResponseEntity.ok(service.getAllByDescricao(descricao));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ProdutoDTO> cadastrar(@RequestBody ProdutoDTO produto){
        return ResponseEntity.ok(service.cadastrar(produto));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<ProdutoDTO> editar(@PathVariable Long id, @RequestBody ProdutoDTO produto){
        return ResponseEntity.ok(service.editar(id, produto));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.ok(String.format("Produto com ID %s foi deletado com sucesso!", id));
    }
}
