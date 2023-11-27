package com.example.uniamerica.pizzaria.controller;

import com.example.uniamerica.pizzaria.dto.EnderecoDTO;
import com.example.uniamerica.pizzaria.service.EnderecosService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/endereco")
@PreAuthorize("hasAnyAuthority('ADMIN')")

public class EnderecoController {
    @Autowired
    private EnderecosService service;

    @GetMapping("/todos")
    public ResponseEntity<List<EnderecoDTO>> findAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<EnderecoDTO> findById(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<EnderecoDTO> post(@RequestBody @Validated EnderecoDTO enderecos) {
        return ResponseEntity.ok(service.post(enderecos));
    }

    @PutMapping("/editar")
    public ResponseEntity<EnderecoDTO> put(@RequestParam @Validated Long id, @RequestBody EnderecoDTO enderecos) {
        return ResponseEntity.ok(service.update(enderecos, id));
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<String> delete(@RequestParam long id) {
        service.delete(id);
        return ResponseEntity.ok(String.format("O endereço com o ID [%s] foi deletado com sucesso!", id));
    }


}
