package com.example.uniamerica.pizzaria.controller;

import com.example.uniamerica.pizzaria.dto.ClienteDTO;
import com.example.uniamerica.pizzaria.dto.UsuarioDTO;
import com.example.uniamerica.pizzaria.entity.Usuario;
import com.example.uniamerica.pizzaria.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/id/{id}")
    @PreAuthorize("hasAnyAuthority('FUNCIONARIO','ADMIN')")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable("id") final String id){
        return ResponseEntity.ok(service.findById(id));
    }
    @GetMapping("/todos")
    @PreAuthorize("hasAnyAuthority('FUNCIONARIO','ADMIN')")
    public ResponseEntity<List<UsuarioDTO>>getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/todos/admin")
    @PreAuthorize("hasAnyAuthority('FUNCIONARIO','ADMIN')")
    public ResponseEntity<List<UsuarioDTO>>getAllAdm(){
        return ResponseEntity.ok(service.getAllAdm());
    }
    @GetMapping("/todos/funcionarios")
    @PreAuthorize("hasAnyAuthority('FUNCIONARIO','ADMIN')")
    public ResponseEntity<List<UsuarioDTO>>getAllFuncionarios(){
        return ResponseEntity.ok(service.getAllFuncionarios());
    }
    @PostMapping("/cadastrar")
    @PreAuthorize("hasAnyAuthority('ADMIN')")

    public ResponseEntity<UsuarioDTO>cadastrar(@RequestBody @Validated UsuarioDTO usuario){
        return ResponseEntity.ok(service.post(usuario));
    }
    @PutMapping("/editar/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<UsuarioDTO>atualizar(@RequestBody @Validated UsuarioDTO usuario, @PathVariable long id){
        return ResponseEntity.ok(service.put(usuario, id));
    }
    @DeleteMapping("/desativar/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<UsuarioDTO>desativar(@PathVariable ("id") final String id){
        return ResponseEntity.ok(service.desativar(id));
    }
    @DeleteMapping("/ativar/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<UsuarioDTO>ativar(@PathVariable ("id") final String id){
        return ResponseEntity.ok(service.ativar(id));
    }
}
