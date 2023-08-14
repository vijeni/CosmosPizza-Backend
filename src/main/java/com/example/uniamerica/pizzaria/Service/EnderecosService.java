package com.example.uniamerica.pizzaria.Service;

import com.example.uniamerica.pizzaria.DTO.EnderecosDTO;
import com.example.uniamerica.pizzaria.Entity.Enderecos;
import org.springframework.stereotype.Service;

@Service
public class EnderecosService {
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
        return enderecosDTO;
    }


}
