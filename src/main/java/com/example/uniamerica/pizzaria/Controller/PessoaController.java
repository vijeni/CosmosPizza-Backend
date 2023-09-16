package com.example.uniamerica.pizzaria.Controller;

import com.example.uniamerica.pizzaria.DTO.PedidoDTO;
import com.example.uniamerica.pizzaria.DTO.PessoaDTO;
import com.example.uniamerica.pizzaria.Repository.PessoaRepository;
import com.example.uniamerica.pizzaria.Service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {
    @Autowired
    PessoaService service;
    @GetMapping
    public ResponseEntity<PessoaDTO>findById(@RequestParam("id") final Long id){
        return ResponseEntity.ok(service.findById(id));
    }
    @GetMapping("/todos")
    public ResponseEntity<List<PessoaDTO>>getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @PostMapping("/cadastrar")
    public ResponseEntity<PessoaDTO>cadastrar(@RequestBody @Validated PessoaDTO pessoa){
        return ResponseEntity.ok(service.post(pessoa));
    }
    @PutMapping("/editar")
    public ResponseEntity<PessoaDTO>atualizar(@RequestBody @Validated PessoaDTO pessoa, @RequestParam long id){
        return ResponseEntity.ok(service.put(pessoa));
    }
    @DeleteMapping("/deletar")
    public ResponseEntity<String>deletar(@RequestParam ("id") final long id){
        service.deletar(id);
        return ResponseEntity.ok(String.format("Pessoa com o id [%s] foi deletado com sucesso.", id));
    }
}
