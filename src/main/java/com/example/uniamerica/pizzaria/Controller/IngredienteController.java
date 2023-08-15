package com.example.uniamerica.pizzaria.Controller;

import com.example.uniamerica.pizzaria.DTO.IngredientDTO;
import com.example.uniamerica.pizzaria.Repository.IngredienteRepository;
import com.example.uniamerica.pizzaria.Service.IngredientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController("/api/ingredientes")
public class IngredienteController {
    @Autowired
    IngredienteRepository repository;
    @Autowired
    IngredientesService service;


    @GetMapping("/id")
    public ResponseEntity<?>findById(@RequestParam("id") final Long id){
        try{
            return ResponseEntity.ok().body(repository.findById(id).orElse(null));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?>post(@RequestBody IngredientDTO ingredientes){
        try{
            return ResponseEntity.ok(service.post(ingredientes));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
