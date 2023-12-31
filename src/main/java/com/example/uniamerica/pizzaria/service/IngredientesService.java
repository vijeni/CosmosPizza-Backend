package com.example.uniamerica.pizzaria.service;

import com.example.uniamerica.pizzaria.dto.IngredienteDTO;
import com.example.uniamerica.pizzaria.entity.Ingrediente;
import com.example.uniamerica.pizzaria.repository.IngredienteRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class IngredientesService {
    @Autowired
    IngredienteRepository repository;

    private final ModelMapper modelMapper = new ModelMapper();

    public Ingrediente toIngredienteEntidade (IngredienteDTO ingredienteDTO){
        return modelMapper.map(ingredienteDTO,Ingrediente.class);
    }

    public IngredienteDTO toIngredienteDTO(Ingrediente ingredienteEntidade){
        return modelMapper.map(ingredienteEntidade,IngredienteDTO.class);
    }

    public IngredienteDTO findByID(long id){
        Ingrediente ingrediente = repository.findById(id).orElse(null);
        Assert.notNull(ingrediente,"Nenhum ingrediente foi localizado com esse ID.");
        return toIngredienteDTO(ingrediente);
    }

    public List<IngredienteDTO> getAll(){
        return repository.findAll().stream().map(this::toIngredienteDTO).toList();
    }

    @Transactional
      public IngredienteDTO post(IngredienteDTO ingredientes) {
        return toIngredienteDTO(repository.save(toIngredienteEntidade(ingredientes)));

    }
    @Transactional
    public IngredienteDTO update(long id, IngredienteDTO ingredientes) {
        Assert.notNull(ingredientes.getId(),"Por favor, insira um ID.");
        return toIngredienteDTO(repository.save(toIngredienteEntidade(ingredientes)));
    }
    @Transactional
    public IngredienteDTO desativar(long id) {
        IngredienteDTO ingrediente = findByID(id);
        ingrediente.desativar();
        return toIngredienteDTO(repository.save(toIngredienteEntidade(ingrediente)));
    }
    @Transactional
    public IngredienteDTO ativar(long id) {
        IngredienteDTO ingrediente = findByID(id);
        ingrediente.ativar();
        return toIngredienteDTO(repository.save(toIngredienteEntidade(ingrediente)));
    }
}
