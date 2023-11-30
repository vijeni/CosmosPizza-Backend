package com.example.uniamerica.pizzaria.service;

import com.example.uniamerica.pizzaria.dto.ClienteDTO;
import com.example.uniamerica.pizzaria.entity.Cliente;
import com.example.uniamerica.pizzaria.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    private final ModelMapper modelMapper = new ModelMapper();

    public Cliente toCliente(ClienteDTO clienteDTO){
        return modelMapper.map(clienteDTO, Cliente.class);
    }

    public ClienteDTO toClienteDTO(Cliente clienteEntidade){
        return modelMapper.map(clienteEntidade, ClienteDTO.class);
    }

    public ClienteDTO findById(long id){
        Cliente cliente = repository.findById(id).orElse(null);
        return toClienteDTO(cliente);
    }

    public List<ClienteDTO>getAll(){
        return repository.findAll().stream().map(this::toClienteDTO).toList();
    }

    @Transactional
    public ClienteDTO post(ClienteDTO cliente) {
        Assert.notNull(cliente.getNome(),"Por favor, digite um nome!");
        Assert.notNull(cliente.getCpf(),"Por favor, digite um CPF!");
        Assert.hasText(cliente.getNome(),"Por favor, digite um nome válido!");
        Assert.hasText(cliente.getCpf(),"Por favor, digite um CPF válido!");
        Assert.notNull(cliente.getTelefone(),"Por favor, digite um telefone!");
        Assert.hasText(cliente.getNome(),"Por favor, digite um telefone válido!");

        final List<Cliente>pessoasCpf = this.repository.findByCpf(cliente.getCpf());
        Assert.isTrue(pessoasCpf.isEmpty(),"Cpf já cadastrado.");

       return toClienteDTO(repository.save(toCliente(cliente)));
    }
    @Transactional
    public ClienteDTO put(ClienteDTO cliente, Long id) {
        Assert.notNull(id, "Por favor, insira um ID!");
        Assert.notNull(cliente.getId(),"Por favor, insira um ID!");
        Assert.notNull(cliente.getCpf(),"Por favor, digite um CPF!");
        Assert.hasText(cliente.getNome(),"Por favor, digite um nome válido!");
        Assert.hasText(cliente.getCpf(),"Por favor, digite um CPF válido!");
        Assert.notNull(cliente.getTelefone(),"Por favor, digite um telefone!");
        Assert.hasText(cliente.getNome(),"Por favor, digite um telefone válido!");

        return toClienteDTO(repository.save(toCliente(cliente)));
    }
    @Transactional
    public ClienteDTO desativar(long id) {
        ClienteDTO cliente = findById(id);
        cliente.desativar();
        return toClienteDTO(repository.save(toCliente(cliente)));

    }
    @Transactional
    public ClienteDTO ativar(long id) {
        ClienteDTO cliente = findById(id);
        cliente.ativar();
        return toClienteDTO(repository.save(toCliente(cliente)));

    }
}
