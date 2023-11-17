package com.example.uniamerica.pizzaria.controller;

import com.example.uniamerica.pizzaria.dto.ProdutoDTO;
import com.example.uniamerica.pizzaria.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
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
    @GetMapping("/todos/ativos")
    public ResponseEntity<List<ProdutoDTO>> getAllAtivos(){
        return ResponseEntity.ok(service.getAllAtivos());
    }
    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<List<ProdutoDTO>> getAllByNome(@PathVariable String descricao){
        return ResponseEntity.ok(service.getAllByDescricao(descricao));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ProdutoDTO> cadastrar(@RequestBody @Validated ProdutoDTO produto){
        return ResponseEntity.ok(service.cadastrar(produto));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<ProdutoDTO> editar(@PathVariable Long id, @Validated @RequestBody ProdutoDTO produto){
        return ResponseEntity.ok(service.editar(id, produto));
    }

    @DeleteMapping("/desativar/{id}")
    public ResponseEntity<ProdutoDTO> deletar(@PathVariable Long id){
        return ResponseEntity.ok(service.deletar(id));
    }

    @PutMapping("/ativar/{id}")
    public ResponseEntity<ProdutoDTO> ativar(@PathVariable Long id){
        return ResponseEntity.ok(service.ativar(id));
    }
}
