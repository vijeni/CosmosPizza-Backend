package com.example.uniamerica.pizzaria.service;

import com.example.uniamerica.pizzaria.dto.ProdutoDTO;
import com.example.uniamerica.pizzaria.entity.Produto;
import com.example.uniamerica.pizzaria.repository.ProdutoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    ProdutoRepository repository;

    private final ModelMapper modelMapper = new ModelMapper();

    public ProdutoDTO toProdutoDTO(Produto produtoEntidade){
        return modelMapper.map(produtoEntidade, ProdutoDTO.class);
    }
    public Produto toProduto(ProdutoDTO produtoDTO){
        return modelMapper.map(produtoDTO, Produto.class);
    }

    public ProdutoDTO findById(Long id){
        Produto produto = repository.findById(id).orElse(null);
        Assert.notNull(produto, "Produto não existe!");
        return toProdutoDTO(produto);
    }
    @Transactional
    public ProdutoDTO cadastrar(ProdutoDTO produtoDTO){
        return toProdutoDTO(repository.save(toProduto(produtoDTO)));
    }

    @Transactional
    public ProdutoDTO editar(Long id, ProdutoDTO produtoDTO) {
        Produto produto = repository.findById(id).orElse(null);
        Assert.notNull(produto, "Produto não existe!");
        return toProdutoDTO(repository.save(toProduto(produtoDTO)));
    }

    @Transactional
    public ProdutoDTO deletar(Long id){
        ProdutoDTO produto = findById(id);
        produto.desativar();
        return toProdutoDTO(repository.save(toProduto(produto)));
    }
    @Transactional
    public ProdutoDTO ativar(Long id){
        ProdutoDTO produto = findById(id);
        produto.ativar();
        return toProdutoDTO(repository.save(toProduto(produto)));
    }

    public List<ProdutoDTO> getAll() {
        return repository.findAll().stream().map(this::toProdutoDTO).toList();
    }
    public List<ProdutoDTO> getAllAtivos() {
        return repository.findAllAtivos().stream().map(this::toProdutoDTO).toList();
    }

    public List<ProdutoDTO> getAllByDescricao(String descricao) {
        return repository.findByDescricaoLike(descricao.trim()).stream().map(this::toProdutoDTO).toList();
    }

    public void validarProdutos(List<ProdutoDTO> produtos) {
        for (ProdutoDTO produto :
                produtos) {
            findById(produto.getId());
        }
    }

    public Double valorProdutos(List<ProdutoDTO> produtos) {
        Double valor = (double) 0;
        for (ProdutoDTO produtoId:
                produtos) {
            ProdutoDTO produto = findById(produtoId.getId());
            valor += produto.getValorUnitario();
        }
        return valor;
    }
}
