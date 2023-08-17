package com.example.uniamerica.pizzaria.Service;

import com.example.uniamerica.pizzaria.DTO.EnderecoDTO;
import com.example.uniamerica.pizzaria.DTO.PessoaDTO;
import com.example.uniamerica.pizzaria.Entity.Endereco;
import com.example.uniamerica.pizzaria.Entity.Pessoa;
import com.example.uniamerica.pizzaria.Repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class EnderecosService {


    @Autowired
    private EnderecoRepository repository;

    private ModelMapper modelMapper = new ModelMapper();

    public Pessoa toPessoa(PessoaDTO pessoaDTO){
        return modelMapper.map(pessoaDTO,Pessoa.class);
    }

    public PessoaDTO toPessoaDTO(Pessoa pessoaEntidade){
        return modelMapper.map(pessoaEntidade, PessoaDTO.class);
    }

    public Endereco toEnderecos (EnderecoDTO enderecoDTO){
        return modelMapper.map(enderecoDTO,Endereco.class);
    }

    public EnderecoDTO toEnderecosDTO (Endereco endereco){

        return modelMapper.map(endereco,EnderecoDTO.class);
    }

    public EnderecoDTO findById(long id){
        Endereco endereco = repository.findById(id).orElse(null);
        Assert.notNull(endereco.getId(),"Lamentamos, nenhum endereço localizado com esse ID.");
        return toEnderecosDTO(endereco);
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
