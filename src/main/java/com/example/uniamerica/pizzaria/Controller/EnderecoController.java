package com.example.uniamerica.pizzaria.Controller;

import com.example.uniamerica.pizzaria.DTO.EnderecoDTO;
import com.example.uniamerica.pizzaria.Repository.EnderecoRepository;
import com.example.uniamerica.pizzaria.Service.EnderecosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController(value = "/api/enderecos")
public class EnderecoController {

    @Autowired
   private EnderecoRepository enderecoRepository;
    @Autowired
    private EnderecosService enderecosService;

    @GetMapping("/id")
    public ResponseEntity<?>findById(@RequestParam("id") final Long id){
    try{
        return ResponseEntity.ok(enderecoRepository.findById(id).orElse(null));
    }catch (Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?>post(@RequestBody EnderecoDTO enderecos){
        try{
            return ResponseEntity.ok(enderecosService.post(enderecos));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/atualizar")
    public ResponseEntity<?>put(@RequestBody EnderecoDTO enderecos, @RequestParam long id){
        try{
            return ResponseEntity.ok(enderecosService.update(enderecos, id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?>delete(@RequestParam long id){
        try{
            enderecosService.delete(id);
            return ResponseEntity.ok(String.format("O endereço com o ID [%s] foi deletado com sucesso!", id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
