package com.example.uniamerica.pizzaria.service;

import com.example.uniamerica.pizzaria.dto.TamanhoDTO;
import com.example.uniamerica.pizzaria.entity.Tamanho;
import com.example.uniamerica.pizzaria.repository.TamanhoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class TamanhoService {
    @Autowired
    TamanhoRepository repository;

    ModelMapper modelMapper = new ModelMapper();
    public Tamanho toTamanho(TamanhoDTO tamanhoDTO){
        return modelMapper.map(tamanhoDTO, Tamanho.class);
    }
    public TamanhoDTO toTamanhoDTO(Tamanho tamanho){
        return modelMapper.map(tamanho, TamanhoDTO.class);
    }
    public TamanhoDTO findById(Long id){
        Tamanho tamanho = repository.findById(id).orElse(null);
        Assert.notNull(tamanho, "Tamanho não existe!");
        return toTamanhoDTO(tamanho);
    }
    @Transactional
    public TamanhoDTO cadastrar(TamanhoDTO tamanhoDTO){
        return toTamanhoDTO(repository.save(toTamanho(tamanhoDTO)));
    }

    @Transactional
    public TamanhoDTO editar(Long id, TamanhoDTO tamanhoDTO) {
        Tamanho tamanho = repository.findById(id).orElse(null);
        Assert.notNull(tamanho, "Tamanho não existe!");
        return toTamanhoDTO(repository.save(toTamanho(tamanhoDTO)));
    }

    @Transactional
    public TamanhoDTO desativar(Long id){
        TamanhoDTO tamanhoDTO = findById(id);
        tamanhoDTO.desativar();
        return toTamanhoDTO(repository.save(toTamanho(tamanhoDTO)));
    }
    @Transactional
    public TamanhoDTO ativar(Long id){
        TamanhoDTO tamanhoDTO = findById(id);
        tamanhoDTO.ativar();
        return toTamanhoDTO(repository.save(toTamanho(tamanhoDTO)));
    }

    public List<TamanhoDTO> getAll() {
        return repository.findAll().stream().map(this::toTamanhoDTO).toList();
    }

    public List<TamanhoDTO> getAllByNome(String nome) {
        return repository.findByNameLike(nome.trim()).stream().map(this::toTamanhoDTO).toList();
    }
}
