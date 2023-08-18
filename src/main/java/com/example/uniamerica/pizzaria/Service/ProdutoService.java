package com.example.uniamerica.pizzaria.Service;

import com.example.uniamerica.pizzaria.DTO.ProdutoDTO;
import com.example.uniamerica.pizzaria.Entity.Produto;
import com.example.uniamerica.pizzaria.Repository.ProdutoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    PizzaService pizzaService;
    private final ModelMapper modelMapper = new ModelMapper();

    public ProdutoDTO toProdutoDTO(Produto produtoEntidade){
        return modelMapper.map(produtoEntidade, ProdutoDTO.class);
    }
    public Produto toProduto(ProdutoDTO produtoDTO){
        return modelMapper.map(produtoDTO, Produto.class);
    }

    public ProdutoDTO findById(Long id){
        Produto produto = produtoRepository.findById(id).orElse(null);
        Assert.notNull(produto, "Produto não existe!");
        return toProdutoDTO(produto);
    }
    @Transactional
    public ProdutoDTO cadastrar(ProdutoDTO produtoDTO){
        pizzaService.validarPizzas(produtoDTO.getPizzas());
        return toProdutoDTO(produtoRepository.save(toProduto(produtoDTO)));
    }

    @Transactional
    public ProdutoDTO editar(Long id, ProdutoDTO produtoDTO) {
        Produto produto = produtoRepository.findById(id).orElse(null);
        Assert.notNull(produto, "Produto não existe!");
        return toProdutoDTO(produtoRepository.save(toProduto(produtoDTO)));
    }

    @Transactional
    public void deletar(Long id){
        Assert.notNull(produtoRepository.findById(id).orElse(null), String.format("Produto com ID %s não encontrado!", id));
        produtoRepository.deleteById(id);
    }

    public List<ProdutoDTO> getAll() {
        return produtoRepository.findAll().stream().map(this::toProdutoDTO).toList();
    }

    public List<ProdutoDTO> getAllByNome(String nome) {
        return produtoRepository.findByNameLike(nome.trim()).stream().map(this::toProdutoDTO).toList();
    }
}
