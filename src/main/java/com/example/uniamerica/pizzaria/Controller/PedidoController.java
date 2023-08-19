package com.example.uniamerica.pizzaria.Controller;

import com.example.uniamerica.pizzaria.DTO.PedidoDTO;
import com.example.uniamerica.pizzaria.Service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {
    @Autowired
    PedidoService service;
    @GetMapping
    public ResponseEntity<?> findById(@RequestParam Long id){
        try{
            return ResponseEntity.ok(service.findById(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/todos")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.ok(service.getAll());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody @Validated  PedidoDTO pedido){
        try{
            return ResponseEntity.ok(service.cadastrar(pedido));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<?> editar(@RequestParam Long codigoPedido, @RequestBody @Validated PedidoDTO pedido){
        try{
            return ResponseEntity.ok(service.editar(codigoPedido, pedido));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/finalizar")
    public ResponseEntity<?> finalizarPedido(@RequestParam Long codigoPedido){
        try{
            return ResponseEntity.ok(service.finalizarPedido(codigoPedido));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?> deletar(@RequestParam Long id){
        try{
            service.deletar(id);
            return ResponseEntity.ok(String.format("Pedido com ID %s foi deletado com sucesso!", id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
