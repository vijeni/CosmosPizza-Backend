package com.example.uniamerica.pizzaria.Controller;

import com.example.uniamerica.pizzaria.DTO.SaborDTO;
import com.example.uniamerica.pizzaria.DTO.TamanhoDTO;
import com.example.uniamerica.pizzaria.Repository.TamanhoRepository;
import com.example.uniamerica.pizzaria.Service.TamanhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tamanho")
public class TamanhoController {

    @Autowired
    TamanhoService service;
    @GetMapping
    public ResponseEntity<?> findById(@RequestParam("id") final Long id){
        try{
            return ResponseEntity.ok(service.findById(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<?>getAll(){
        try {
            return ResponseEntity.ok(service.getAll());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?>cadastrar(@RequestBody @Validated TamanhoDTO tamanhoDTO){
        try{
            return ResponseEntity.ok(service.cadastrar(tamanhoDTO));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<?>editar(@RequestBody @Validated TamanhoDTO tamanhoDTO, @RequestParam("id")final long id){
        try {
            return ResponseEntity.ok(service.editar(id, tamanhoDTO));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("deletar")
    public ResponseEntity<?>deletar(@RequestParam("id") final long id){
        try{
            service.deletar(id);
            return ResponseEntity.ok(String.format("O tamanho com o id [%s] foi deletado com sucesso.", id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
