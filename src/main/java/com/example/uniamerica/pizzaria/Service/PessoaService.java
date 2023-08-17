package com.example.uniamerica.pizzaria.Service;

import com.example.uniamerica.pizzaria.DTO.PessoaDTO;
import com.example.uniamerica.pizzaria.Entity.Pessoa;
import com.example.uniamerica.pizzaria.Repository.PessoaRepository;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.hibernate.query.derived.AnonymousTupleSimpleSqmPathSource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    private ModelMapper modelMapper = new ModelMapper();

    public Pessoa toPessoa(PessoaDTO pessoaDTO){
        return modelMapper.map(pessoaDTO,Pessoa.class);
    }

    public PessoaDTO toPessoaDTO(Pessoa pessoaEntidade){
        return modelMapper.map(pessoaEntidade, PessoaDTO.class);
    }

    public PessoaDTO post(PessoaDTO pessoa) {
        Assert.notNull(pessoa.getNome(),"Por favor, digite um nome!");
        Assert.notNull(pessoa.getCpf(),"Por favor, digite um CPF!");
        Assert.hasText(pessoa.getNome(),"Por favor, digite um nome válido!");
        Assert.hasText(pessoa.getCpf(),"Por favor, digite um CPF válido!");
        Assert.notNull(pessoa.getTelefone(),"Por favor, digite um telefone!");
        Assert.hasText(pessoa.getNome(),"Por favor, digite um telefone válido!");

       return toPessoaDTO(repository.save(toPessoa(pessoa)));
    }

    public PessoaDTO put(PessoaDTO pessoa) {
        Assert.notNull(pessoa.getId(),"Por favor, insira um ID!");
        Assert.notNull(pessoa.getCpf(),"Por favor, digite um CPF!");
        Assert.hasText(pessoa.getNome(),"Por favor, digite um nome válido!");
        Assert.hasText(pessoa.getCpf(),"Por favor, digite um CPF válido!");
        Assert.notNull(pessoa.getTelefone(),"Por favor, digite um telefone!");
        Assert.hasText(pessoa.getNome(),"Por favor, digite um telefone válido!");

        return toPessoaDTO(repository.save(toPessoa(pessoa)));
    }

    public void deletar(long id) {
        Assert.notNull(repository.findById(id).orElse(null), String.format("Nenhuma pessoa localizada com o ID [%s]", id));

        repository.deleteById(id);

    }
}
