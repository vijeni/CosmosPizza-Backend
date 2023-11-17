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
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/id/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable("id") final Long id){
        return ResponseEntity.ok(service.findById(id));
    }
    @GetMapping("/todos")
    public ResponseEntity<List<UsuarioDTO>>getAll(){

        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/todos/usuarios")
    public ResponseEntity<List<UsuarioDTO>>getAllAdm(){
        return ResponseEntity.ok(service.getAllAdm());
    }
    @GetMapping("/todos/funcionarios")
    public ResponseEntity<List<UsuarioDTO>>getAllFuncionarios(){
        return ResponseEntity.ok(service.getAllFuncionarios());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioDTO>cadastrar(@RequestBody @Validated UsuarioDTO usuario){
        return ResponseEntity.ok(service.post(usuario));
    }
    @PutMapping("/editar/{id}")
    public ResponseEntity<UsuarioDTO>atualizar(@RequestBody @Validated UsuarioDTO usuario, @PathVariable long id){
        return ResponseEntity.ok(service.put(usuario, id));
    }
    @DeleteMapping("/desativar/{id}")
    public ResponseEntity<UsuarioDTO>desativar(@PathVariable ("id") final long id){
        return ResponseEntity.ok(service.desativar(id));
    }
    @DeleteMapping("/ativar/{id}")
    public ResponseEntity<UsuarioDTO>ativar(@PathVariable ("id") final long id){
        return ResponseEntity.ok(service.ativar(id));
    }

    @GetMapping("/teste/admin")
    public String testeAdm(){
        return "<h4>Se você estiver vendo essa tela, é porque você é admin!</h4>";
    }

    @GetMapping("/teste/funcionario")
    public String testeFuncionario(){
        return "<h4>Se você estiver vendo essa tela, é porque você é funcionário!</h4>";
    }
}
