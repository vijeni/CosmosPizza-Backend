package com.example.uniamerica.pizzaria.Service;

import com.example.uniamerica.pizzaria.DTO.ProdutoDTO;
import com.example.uniamerica.pizzaria.Entity.Produto;
import com.example.uniamerica.pizzaria.Repository.ProdutoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class ProdutoService {
    @Autowired
    ProdutoRepository repository;
    private final ModelMapper modelMapper =new ModelMapper();;
    public ProdutoDTO findById(Long id){
        Produto produto = repository.findById(id).orElse(null);
        Assert.notNull(produto, "Produto não existe");
        return modelMapper.map(produto, ProdutoDTO.class);
    }
}
