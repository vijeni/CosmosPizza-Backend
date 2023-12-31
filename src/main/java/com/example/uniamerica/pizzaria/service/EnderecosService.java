package com.example.uniamerica.pizzaria.service;

import com.example.uniamerica.pizzaria.dto.EnderecoDTO;
import com.example.uniamerica.pizzaria.entity.Endereco;
import com.example.uniamerica.pizzaria.repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class EnderecosService {


    @Autowired
    private EnderecoRepository repository;

    private final ModelMapper modelMapper = new ModelMapper();
    public Endereco toEnderecos (EnderecoDTO enderecoDTO){
        return modelMapper.map(enderecoDTO,Endereco.class);
    }

    public EnderecoDTO toEnderecosDTO (Endereco endereco){

        return modelMapper.map(endereco,EnderecoDTO.class);
    }

    public EnderecoDTO findById(long id){
        Endereco endereco = repository.findById(id).orElse(null);
        Assert.notNull(endereco,"Lamentamos, nenhum endereço localizado com esse ID.");
        return toEnderecosDTO(endereco);
    }

    public List<EnderecoDTO>listAll(){
        return repository.findAll().stream().map(this::toEnderecosDTO).toList();
    }

    @Transactional
    public EnderecoDTO post(EnderecoDTO enderecoDTO) {
        Assert.notNull(enderecoDTO.getBairro(),"Por favor, informe um bairro!");
        Assert.notNull(enderecoDTO.getCep(),"Por favor, informe um CEP!");
        Assert.hasText(enderecoDTO.getBairro(),"Informe um bairro válido!");
        Assert.hasText(enderecoDTO.getCep(),"Informe um CEP válido!");

        return toEnderecosDTO(repository.save(toEnderecos(enderecoDTO)));
    }

    @Transactional
    public EnderecoDTO update (EnderecoDTO enderecoDTO, Long id){
        Assert.notNull(enderecoDTO.getId(),"Por favor, insira um ID!");
        Assert.notNull(enderecoDTO.getBairro(),"Por favor, informe um bairro!");
        Assert.notNull(enderecoDTO.getCep(),"Por favor, informe um CEP!");
        Assert.hasText(enderecoDTO.getBairro(),"Informe um bairro válido!");
        Assert.hasText(enderecoDTO.getCep(),"Informe um CEP válido!");

        return toEnderecosDTO(repository.save(toEnderecos(enderecoDTO)));
    }

    @Transactional
    public void delete (long id){

        Endereco endereco = repository.findById(id).orElse(null);
        Assert.notNull(endereco,String.format("Endereço com o id [%s] não foi localizado!",id));
        repository.deleteById(id);
    }

}
