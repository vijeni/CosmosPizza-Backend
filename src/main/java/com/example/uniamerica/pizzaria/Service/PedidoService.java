package com.example.uniamerica.pizzaria.Service;

import com.example.uniamerica.pizzaria.DTO.PedidoDTO;
import com.example.uniamerica.pizzaria.Entity.Pedido;
import com.example.uniamerica.pizzaria.Repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
    @Autowired
    PedidoRepository pedidoRepository;
}
