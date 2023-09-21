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
public class PedidoController {
    @Autowired
    PedidoService service;
    @GetMapping
    public ResponseEntity<PedidoDTO> findById(@RequestParam Long id){
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

    @PutMapping("/editar")
    public ResponseEntity<PedidoDTO> editar(@RequestParam Long codigoPedido, @RequestBody @Validated PedidoDTO pedido){
        return ResponseEntity.ok(service.editar(codigoPedido, pedido));
    }

    @PutMapping("/finalizar")
    public ResponseEntity<PedidoDTO> finalizarPedido(@RequestParam Long codigoPedido){
        return ResponseEntity.ok(service.finalizarPedido(codigoPedido));
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<String> deletar(@RequestParam Long id){
        service.deletar(id);
        return ResponseEntity.ok(String.format("Pedido com ID %s foi deletado com sucesso!", id));
    }
}
