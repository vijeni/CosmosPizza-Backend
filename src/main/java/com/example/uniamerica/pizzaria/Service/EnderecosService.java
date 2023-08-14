package com.example.uniamerica.pizzaria.Service;

import com.example.uniamerica.pizzaria.DTO.EnderecosDTO;
import com.example.uniamerica.pizzaria.Entity.Enderecos;
import com.example.uniamerica.pizzaria.Repository.EnderecosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class EnderecosService {


    @Autowired
    private EnderecosRepository repository;
    public Enderecos toEnderecos (EnderecosDTO enderecosDTO){
        Enderecos enderecos = new Enderecos();
        enderecos.setBairro(enderecosDTO.getBairro());
        enderecos.setCep(enderecosDTO.getCep());
        enderecos.setComplemento(enderecosDTO.getComplemento());
        enderecos.setNumero(enderecosDTO.getNumero());
        enderecos.setLogradouro(enderecosDTO.getLogradouro());
        return enderecos;
    }

    public EnderecosDTO toEnderecosDTO (Enderecos enderecos){
        EnderecosDTO enderecosDTO = new EnderecosDTO();
        enderecosDTO.setBairro(enderecos.getBairro());
        enderecosDTO.setCep(enderecos.getCep());
        enderecosDTO.setComplemento(enderecos.getComplemento());
        enderecosDTO.setNumero(enderecos.getNumero());
        enderecosDTO.setLogradouro(enderecos.getLogradouro());
        return null;
    }

    @Transactional
    public EnderecosDTO post(EnderecosDTO enderecosDTO) {
        Assert.notNull(enderecosDTO.getBairro(),"Por favor, informe um bairro!");
        Assert.notNull(enderecosDTO.getCep(),"Por favor, informe um CEP!");
        Assert.hasText(enderecosDTO.getBairro(),"Informe um bairro válido!");
        Assert.hasText(enderecosDTO.getCep(),"Informe um CEP válido!");


        return toEnderecosDTO(repository.save(toEnderecos(enderecosDTO)));
    }

    @Transactional
    public EnderecosDTO update (EnderecosDTO enderecosDTO, long id){
        Assert.notNull(enderecosDTO.getId(),"Por favor, insira um ID!");
        Assert.notNull(enderecosDTO.getBairro(),"Por favor, informe um bairro!");
        Assert.notNull(enderecosDTO.getCep(),"Por favor, informe um CEP!");
        Assert.hasText(enderecosDTO.getBairro(),"Informe um bairro válido!");
        Assert.hasText(enderecosDTO.getCep(),"Informe um CEP válido!");

        return toEnderecosDTO(repository.save(toEnderecos(enderecosDTO)));
    }

    @Transactional
    public void delete (long id){

        Enderecos enderecos = repository.findById(id).orElse(null);
        Assert.notNull(enderecos,String.format("Endereço com o id [%s] não foi localizado!",id));
        repository.deleteById(id);
    }

}
