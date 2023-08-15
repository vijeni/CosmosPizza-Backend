package com.example.uniamerica.pizzaria.Controller;

import com.example.uniamerica.pizzaria.DTO.EnderecosDTO;
import com.example.uniamerica.pizzaria.DTO.IngredientesDTO;
import com.example.uniamerica.pizzaria.Repository.IngredientesRepository;
import com.example.uniamerica.pizzaria.Service.IngredientesService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController("/api/ingredientes")
public class IngredientesController {
    @Autowired
    IngredientesRepository repository;
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
    public ResponseEntity<?>post(@RequestBody IngredientesDTO ingredientes){
        try{
            return ResponseEntity.ok(service.post(ingredientes));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/atualizar")
    public ResponseEntity<?>put(@RequestParam("id") Long id, @RequestBody IngredientesDTO ingredientes){
        try{
            return null;
        }catch (Exception e){
           return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
