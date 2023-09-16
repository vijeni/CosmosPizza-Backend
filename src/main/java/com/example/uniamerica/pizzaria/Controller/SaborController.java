package com.example.uniamerica.pizzaria.Controller;

import com.example.uniamerica.pizzaria.DTO.ProdutoDTO;
import com.example.uniamerica.pizzaria.DTO.SaborDTO;
import com.example.uniamerica.pizzaria.Repository.SaborRepository;
import com.example.uniamerica.pizzaria.Service.SaborService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/sabor")
public class SaborController {
    @Autowired
    SaborService service;
    @GetMapping
    public ResponseEntity<SaborDTO>findById(@RequestParam("id") final long id){
        return ResponseEntity.ok(service.findById(id));
    }
    @GetMapping("/todos")
    public ResponseEntity<List<SaborDTO>>getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<SaborDTO>cadastrar(@RequestBody @Validated SaborDTO sabor){
        return ResponseEntity.ok(service.cadastrar(sabor));
    }

    @PutMapping("/editar")
    public ResponseEntity<SaborDTO>editar(@RequestBody @Validated SaborDTO sabor, @RequestParam("id")final long id){
        return ResponseEntity.ok(service.update(sabor,id));
    }
    @DeleteMapping("deletar")
    public ResponseEntity<String>deletar(@RequestParam("id") final long id){
        service.delete(id);
        return ResponseEntity.ok(String.format("O sabor com o id [%s] foi deletado com sucesso.", id));
    }
}
