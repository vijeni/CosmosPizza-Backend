package com.example.uniamerica.pizzaria.controller;

import com.example.uniamerica.pizzaria.dto.PessoaDTO;
import com.example.uniamerica.pizzaria.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoa")
@CrossOrigin(origins="http://localhost:4200")

public class PessoaController {
    @Autowired
    PessoaService service;
    @GetMapping("/id/{id}")
    public ResponseEntity<PessoaDTO>findById(@PathVariable("id") final Long id){
        return ResponseEntity.ok(service.findById(id));
    }
    @GetMapping("/todos")
    public ResponseEntity<List<PessoaDTO>>getAll(){

        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/todos/clientes")
    public ResponseEntity<List<PessoaDTO>>getAllClientes(){
        return ResponseEntity.ok(service.getAllClientes());
    }
    @GetMapping("/todos/funcionarios")
    public ResponseEntity<List<PessoaDTO>>getAllFuncionarios(){
        return ResponseEntity.ok(service.getAllFuncionarios());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<PessoaDTO>cadastrar(@RequestBody @Validated PessoaDTO pessoa){
        return ResponseEntity.ok(service.post(pessoa));
    }
    @PutMapping("/editar/{id}")
    public ResponseEntity<PessoaDTO>atualizar(@RequestBody @Validated PessoaDTO pessoa, @PathVariable long id){
        return ResponseEntity.ok(service.put(pessoa, id));
    }
    @DeleteMapping("/desativar/{id}")
    public ResponseEntity<PessoaDTO>desativar(@PathVariable ("id") final long id){
        return ResponseEntity.ok(service.desativar(id));
    }
    @DeleteMapping("/ativar/{id}")
    public ResponseEntity<PessoaDTO>ativar(@PathVariable ("id") final long id){
        return ResponseEntity.ok(service.ativar(id));
    }
}
