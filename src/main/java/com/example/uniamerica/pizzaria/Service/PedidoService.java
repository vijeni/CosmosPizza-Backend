package com.example.uniamerica.pizzaria.Service;

import com.example.uniamerica.pizzaria.DTO.PedidoDTO;
import com.example.uniamerica.pizzaria.Entity.Pedido;
import com.example.uniamerica.pizzaria.Repository.PedidoRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoService {
    @Autowired
    PedidoRepository repository;
    private ModelMapper modelMapper = new ModelMapper();

    public PedidoDTO toPedidoDTO(Pedido pedido){
        return modelMapper.map(pedido, PedidoDTO.class);
    }
    public Pedido toPedido(PedidoDTO pedidoDTO){
        return modelMapper.map(pedidoDTO, Pedido.class);
    }
    public PedidoDTO findById(Long id) {
        Pedido pedido = repository.findById(id).orElse(null);
        Assert.notNull(pedido, String.format("Pedido com ID %s não existe!", id));
        return toPedidoDTO(pedido);
    }

    public List<PedidoDTO> getAll() {
        return repository.findAll().stream().map(this::toPedidoDTO).toList();
    }

    @Transactional
    public PedidoDTO cadastrar(PedidoDTO pedidoDTO) {
        return toPedidoDTO(repository.save(toPedido(pedidoDTO)));
    }

    @Transactional
    public PedidoDTO editar(Long id, PedidoDTO pedidoDTO) {
        Assert.notNull(repository.findById(id).orElse(null), String.format("Pedido com ID %s não exite!"));
        return toPedidoDTO(repository.save(toPedido(pedidoDTO)));
    }

    public void deletar(Long id) {
        Assert.notNull(repository.findById(id).orElse(null), String.format("Pedido com ID %s não exite!"));
        repository.deleteById(id);
    }
}
