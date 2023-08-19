package com.example.uniamerica.pizzaria.Service;

import com.example.uniamerica.pizzaria.DTO.TamanhoDTO;
import com.example.uniamerica.pizzaria.Entity.Tamanho;
import com.example.uniamerica.pizzaria.Repository.TamanhoRepository;
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
    private Tamanho toTamanho(TamanhoDTO tamanhoDTO){
        return modelMapper.map(tamanhoDTO, Tamanho.class);
    }
    private TamanhoDTO toTamanhoDTO(Tamanho tamanho){
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
    public void deletar(Long id){
        Assert.notNull(repository.findById(id).orElse(null), String.format("Tamanho com ID %s não encontrado!", id));
        repository.deleteById(id);
    }

    public List<TamanhoDTO> getAll() {
        return repository.findAll().stream().map(this::toTamanhoDTO).toList();
    }

    public List<TamanhoDTO> getAllByNome(String nome) {
        return repository.findByNameLike(nome.trim()).stream().map(this::toTamanhoDTO).toList();
    }
}
