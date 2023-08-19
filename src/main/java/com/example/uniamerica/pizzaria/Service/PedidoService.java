package com.example.uniamerica.pizzaria.Service;

import com.example.uniamerica.pizzaria.DTO.PedidoDTO;
import com.example.uniamerica.pizzaria.Entity.Pedido;
import com.example.uniamerica.pizzaria.Repository.PedidoRepository;
import com.example.uniamerica.pizzaria.Repository.PessoaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {
    @Autowired
    PizzaService pizzaService;
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    PessoaRepository pessoaRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public PedidoDTO toPedidoDTO(Pedido pedido){
        return modelMapper.map(pedido, PedidoDTO.class);
    }
    public Pedido toPedido(PedidoDTO pedidoDTO){
        return modelMapper.map(pedidoDTO, Pedido.class);
    }
    public PedidoDTO findById(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        Assert.notNull(pedido, String.format("Pedido com ID %s não existe!", id));
        return toPedidoDTO(pedido);
    }

    public List<PedidoDTO> getAll() {
        return pedidoRepository.findAll().stream().map(this::toPedidoDTO).toList();
    }

    public PedidoDTO validaPedido(PedidoDTO pedidoDTO){
        Assert.notNull(pessoaRepository.findById(pedidoDTO.getCliente().getId()).orElse(null), "Cliente informado não existe!");
        Assert.notNull(pessoaRepository.findById(pedidoDTO.getFuncionario().getId()).orElse(null), "Funcionário informado não existe!");
        pedidoDTO.setValorTotal(pedidoDTO.getValorPedido() + pedidoDTO.getValorEntrega());
        if(pedidoDTO.getDataAbertura() == null){
            pedidoDTO.setDataAbertura(LocalDateTime.now());
        }
        if(pedidoDTO.getDataConclusao() != null){
            Assert.isTrue(pedidoDTO.getDataAbertura().isBefore(pedidoDTO.getDataConclusao()), "Data de abertura não deve ser depois da data de conclusão do pedido!");
        }
        return pedidoDTO;
    }

    @Transactional
    public PedidoDTO cadastrar(PedidoDTO pedidoDTO) {
        pizzaService.validarPizzas(pedidoDTO.getPizzas());
        return toPedidoDTO(pedidoRepository.save(toPedido(validaPedido(pedidoDTO))));
    }

    @Transactional
    public PedidoDTO editar(Long codigoPedido, PedidoDTO pedidoDTO) {
        Assert.notNull(pedidoDTO.getCodigoPedido(), "Código do Pedido não informado!");
        Assert.isTrue(pedidoDTO.getCodigoPedido().equals(codigoPedido), "Pedido a ser editado não é o mesmo informado!");
        Assert.notNull(pedidoRepository.findById(codigoPedido).orElse(null), String.format("Pedido com código %s não exite!", codigoPedido));
        return toPedidoDTO(pedidoRepository.save(toPedido(validaPedido(pedidoDTO))));
    }

    public void deletar(Long id) {
        Assert.notNull(pedidoRepository.findById(id).orElse(null), String.format("Pedido com ID %s não exite!"));
        pedidoRepository.deleteById(id);
    }
}
