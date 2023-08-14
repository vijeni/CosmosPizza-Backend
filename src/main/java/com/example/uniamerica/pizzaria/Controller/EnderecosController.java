package com.example.uniamerica.pizzaria.Controller;

import com.example.uniamerica.pizzaria.DTO.EnderecosDTO;
import com.example.uniamerica.pizzaria.Entity.Enderecos;
import com.example.uniamerica.pizzaria.Repository.EnderecosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController(value = "/api/enderecos")
public class EnderecosController {

    @Autowired
   private EnderecosRepository enderecosRepository;
    @GetMapping("/id")
    public ResponseEntity<?>findById(@RequestParam("id") final Long id){
    try{
        return ResponseEntity.ok(enderecosRepository.findById(id).orElse(null));
    }catch (Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
        }
    }





}
