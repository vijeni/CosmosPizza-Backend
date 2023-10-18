package com.example.uniamerica.pizzaria.service;

import com.example.uniamerica.pizzaria.dto.PedidoDTO;
import com.example.uniamerica.pizzaria.entity.Pedido;
import com.example.uniamerica.pizzaria.entity.Status;
import com.example.uniamerica.pizzaria.repository.PedidoRepository;
import com.example.uniamerica.pizzaria.repository.PessoaRepository;
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
    @Autowired
    ProdutoService produtoService;

    private final ModelMapper modelMapper = new ModelMapper();

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
        if(pedidoDTO.getDataAbertura() == null){
            pedidoDTO.setDataAbertura(LocalDateTime.now());
        }
        if(pedidoDTO.getDataConclusao() != null){
            Assert.isTrue(pedidoDTO.getDataAbertura().isBefore(pedidoDTO.getDataConclusao()), "Data de abertura não deve ser depois da data de conclusão do pedido!");
        }
        return calculaValores(pedidoDTO);
    }

    private PedidoDTO calculaValores(PedidoDTO pedidoDTO) {
        pedidoDTO.setValorTotal(0D);
        if(!pedidoDTO.getPizzas().isEmpty()) {
            Double valorPizzas = pizzaService.valorPizzas(pedidoDTO.getPizzas());
            pedidoDTO.addValorPedido(valorPizzas);
        }
        if(!pedidoDTO.getProdutos().isEmpty()) {
            Double valorProdutos = produtoService.valorProdutos(pedidoDTO.getProdutos());
            pedidoDTO.addValorPedido(valorProdutos);
        }
        if(pedidoDTO.getValorEntrega() != null){
            pedidoDTO.addValorTotal(pedidoDTO.getValorEntrega() + pedidoDTO.getValorPedido());
        }else{
            pedidoDTO.addValorTotal(pedidoDTO.getValorPedido());
        }
        return pedidoDTO;
    }

    @Transactional
    public PedidoDTO cadastrar(PedidoDTO pedidoDTO) {
        if(!pedidoDTO.getPizzas().isEmpty()) {
            pizzaService.validarPizzas(pedidoDTO.getPizzas());
        }
        if(!pedidoDTO.getProdutos().isEmpty()) {
            produtoService.validarProdutos(pedidoDTO.getProdutos());
        }
        return toPedidoDTO(pedidoRepository.save(toPedido(validaPedido(pedidoDTO))));
    }

    @Transactional
    public PedidoDTO editar(Long codigoPedido, PedidoDTO pedidoDTO) {
        Assert.notNull(pedidoDTO.getId(), "Código do Pedido não informado!");
        Assert.isTrue(pedidoDTO.getId().equals(codigoPedido), "Pedido a ser editado não é o mesmo informado!");
        Assert.notNull(pedidoRepository.findById(codigoPedido).orElse(null), String.format("Pedido com código %s não exite!", codigoPedido));
        if(!pedidoDTO.getPizzas().isEmpty()) {
            pizzaService.validarPizzas(pedidoDTO.getPizzas());
        }
        if(!pedidoDTO.getProdutos().isEmpty()) {
            produtoService.validarProdutos(pedidoDTO.getProdutos());
        }
        return toPedidoDTO(pedidoRepository.save(toPedido(validaPedido(pedidoDTO))));
    }

    public void deletar(Long id) {
        Assert.notNull(pedidoRepository.findById(id).orElse(null), "Pedido com ID %s não exite!");
        pedidoRepository.deleteById(id);
    }

    public PedidoDTO finalizarPedido(Long codigoPedido) {
        PedidoDTO pedidoDTO = toPedidoDTO(pedidoRepository.findById(codigoPedido).orElse(null));
        Assert.notNull(pedidoDTO, String.format( "Pedido com ID %s não existe!", codigoPedido));
        Assert.isTrue(!pedidoDTO.getStatus().equals(Status.ENCERRADO), "Esse pedido já foi finalizado!");
        pedidoDTO.setStatus(Status.ENCERRADO);
        return toPedidoDTO(pedidoRepository.save(toPedido(pedidoDTO)));
    }
}
