package com.example.uniamerica.pizzaria.service;

import com.example.uniamerica.pizzaria.dto.PessoaDTO;
import com.example.uniamerica.pizzaria.entity.Pessoa;
import com.example.uniamerica.pizzaria.repository.PessoaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    private final ModelMapper modelMapper = new ModelMapper();

    public Pessoa toPessoa(PessoaDTO pessoaDTO){
        return modelMapper.map(pessoaDTO,Pessoa.class);
    }

    public PessoaDTO toPessoaDTO(Pessoa pessoaEntidade){
        return modelMapper.map(pessoaEntidade, PessoaDTO.class);
    }

    public PessoaDTO findById(long id){
        Pessoa pessoa = repository.findById(id).orElse(null);
        return toPessoaDTO(pessoa);
    }

    public List<PessoaDTO>getAll(){
        return repository.findAll().stream().map(this::toPessoaDTO).toList();
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

    public PessoaDTO put(PessoaDTO pessoa, Long id) {
        Assert.notNull(id, "Por favor, insira um ID!");
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
