package com.example.uniamerica.pizzaria.Controller;

import com.example.uniamerica.pizzaria.Repository.IngredientesRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController("/api/ingredientes")
public class IngredientesController {
    @Autowired
    IngredientesRepository repository;

    @GetMapping("/id")
    public ResponseEntity<?>findById(@RequestParam long id){
        try{
            return ResponseEntity.ok().body(repository.findById(id).orElse(null));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}
