package com.example.uniamerica.pizzaria.controller;

import com.example.uniamerica.pizzaria.dto.PedidoDTO;
import com.example.uniamerica.pizzaria.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedido")
@CrossOrigin(origins="http://localhost:4200")

public class PedidoController {
    @Autowired
    PedidoService service;
    @GetMapping("/id/{id}")
    public ResponseEntity<PedidoDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }
    @GetMapping("/todos")
    public ResponseEntity<List<PedidoDTO>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @PostMapping("/cadastrar")
    public ResponseEntity<PedidoDTO> cadastrar(@RequestBody @Validated  PedidoDTO pedido){
        return ResponseEntity.ok(service.cadastrar(pedido));
    }
    @PutMapping("/editar/{id}")
    public ResponseEntity<PedidoDTO> editar(@PathVariable Long id, @RequestBody @Validated PedidoDTO pedido){
        return ResponseEntity.ok(service.editar(id, pedido));
    }
    @PutMapping("/finalizar/{id}")
    public ResponseEntity<PedidoDTO> finalizarPedido(@PathVariable Long id){
        return ResponseEntity.ok(service.finalizarPedido(id));
    }
    @DeleteMapping("/cancelar/{id}")
    public ResponseEntity<PedidoDTO> cancelar(@PathVariable Long id){
        return ResponseEntity.ok(service.cancelar(id));
    }
    @PutMapping("/reabrir/{id}")
    public ResponseEntity<PedidoDTO> reabrir(@PathVariable Long id){
        return ResponseEntity.ok(service.reabrir(id));
    }
}
