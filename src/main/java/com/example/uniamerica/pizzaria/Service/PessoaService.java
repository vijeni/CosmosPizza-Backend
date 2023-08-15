package com.example.uniamerica.pizzaria.Service;

import com.example.uniamerica.pizzaria.DTO.PessoaDTO;
import com.example.uniamerica.pizzaria.Entity.Pessoa;
import com.example.uniamerica.pizzaria.Repository.PessoaRepository;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.hibernate.query.derived.AnonymousTupleSimpleSqmPathSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public Pessoa toPessoa(PessoaDTO pessoaDTO){
        Pessoa pessoaEntidade = new Pessoa();
        pessoaEntidade.setTipoPessoa(pessoaDTO.getTipoPessoa());
        pessoaEntidade.setNome(pessoaDTO.getNome());
        pessoaEntidade.setCpf(pessoaDTO.getCpf());
        pessoaEntidade.setTelefone(pessoaDTO.getTelefone());

        return pessoaEntidade;
    }

    public PessoaDTO toPessoaDTO(Pessoa pessoaEntidade){
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setTipoPessoa(pessoaEntidade.getTipoPessoa());
        pessoaDTO.setNome(pessoaEntidade.getNome());
        pessoaDTO.setCpf(pessoaEntidade.getCpf());
        pessoaDTO.setTelefone(pessoaEntidade.getTelefone());

        return pessoaDTO;
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
        Assert.notNull(pessoa.getNome(),"Por favor, digite um nome!");
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
