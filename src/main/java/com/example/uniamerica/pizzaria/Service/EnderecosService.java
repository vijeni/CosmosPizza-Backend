package com.example.uniamerica.pizzaria.Service;

import com.example.uniamerica.pizzaria.DTO.EnderecoDTO;
import com.example.uniamerica.pizzaria.Entity.Endereco;
import com.example.uniamerica.pizzaria.Repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class EnderecosService {


    @Autowired
    private EnderecoRepository repository;
    public Endereco toEnderecos (EnderecoDTO enderecoDTO){
        Endereco endereco = new Endereco();
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setCep(enderecoDTO.getCep());
        endereco.setComplemento(enderecoDTO.getComplemento());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setLogradouro(enderecoDTO.getLogradouro());
        return endereco;
    }

    public EnderecoDTO toEnderecosDTO (Endereco endereco){
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setBairro(endereco.getBairro());
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setComplemento(endereco.getComplemento());
        enderecoDTO.setNumero(endereco.getNumero());
        enderecoDTO.setLogradouro(endereco.getLogradouro());
        return enderecoDTO;
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
    public EnderecoDTO update (EnderecoDTO enderecoDTO, long id){
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
