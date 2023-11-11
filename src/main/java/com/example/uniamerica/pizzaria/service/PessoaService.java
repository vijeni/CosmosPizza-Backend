package com.example.uniamerica.pizzaria.service;

import com.example.uniamerica.pizzaria.dto.PessoaDTO;
import com.example.uniamerica.pizzaria.entity.Cliente;
import com.example.uniamerica.pizzaria.entity.Role;
import com.example.uniamerica.pizzaria.repository.PessoaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    private final ModelMapper modelMapper = new ModelMapper();

    public Cliente toPessoa(PessoaDTO pessoaDTO){
        return modelMapper.map(pessoaDTO, Cliente.class);
    }

    public PessoaDTO toPessoaDTO(Cliente clienteEntidade){
        return modelMapper.map(clienteEntidade, PessoaDTO.class);
    }

    public PessoaDTO findById(long id){
        Cliente cliente = repository.findById(id).orElse(null);
        return toPessoaDTO(cliente);
    }

    public List<PessoaDTO>getAll(){
        return repository.findAll().stream().map(this::toPessoaDTO).toList();
    }
    public List<PessoaDTO>getAllClientes(){
        return repository.findAllByTipo(Role.CLIENTE).stream().map(this::toPessoaDTO).toList();
    }
    public List<PessoaDTO>getAllFuncionarios(){
        return repository.findAllByTipo(Role.FUNCIONARIO).stream().map(this::toPessoaDTO).toList();
    }

    @Transactional
    public PessoaDTO post(PessoaDTO pessoa) {
        Assert.notNull(pessoa.getNome(),"Por favor, digite um nome!");
        Assert.notNull(pessoa.getCpf(),"Por favor, digite um CPF!");
        Assert.hasText(pessoa.getNome(),"Por favor, digite um nome válido!");
        Assert.hasText(pessoa.getCpf(),"Por favor, digite um CPF válido!");
        Assert.notNull(pessoa.getTelefone(),"Por favor, digite um telefone!");
        Assert.hasText(pessoa.getNome(),"Por favor, digite um telefone válido!");

        final List<Cliente>pessoasCpf = this.repository.findByCpf(pessoa.getCpf());
        Assert.isTrue(pessoasCpf.isEmpty(),"Cpf já cadastrado.");

       return toPessoaDTO(repository.save(toPessoa(pessoa)));
    }
    @Transactional
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
    @Transactional
    public PessoaDTO desativar(long id) {
        PessoaDTO pessoa = findById(id);
        pessoa.desativar();
        return toPessoaDTO(repository.save(toPessoa(pessoa)));

    }
    @Transactional
    public PessoaDTO ativar(long id) {
        PessoaDTO pessoa = findById(id);
        pessoa.ativar();
        return toPessoaDTO(repository.save(toPessoa(pessoa)));

    }
}
