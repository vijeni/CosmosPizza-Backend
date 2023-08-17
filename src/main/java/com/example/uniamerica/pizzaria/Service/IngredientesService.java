package com.example.uniamerica.pizzaria.Service;

import com.example.uniamerica.pizzaria.DTO.IngredienteDTO;
import com.example.uniamerica.pizzaria.Entity.Ingrediente;
import com.example.uniamerica.pizzaria.Repository.IngredienteRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
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

    private ModelMapper modelMapper = new ModelMapper();

    public Ingrediente toIngredienteEntidade (IngredienteDTO ingredienteDTO){
        return modelMapper.map(ingredienteDTO,Ingrediente.class);
    }

    public IngredienteDTO toIngredienteDTO(Ingrediente ingredienteEntidade){
        return modelMapper.map(ingredienteEntidade,IngredienteDTO.class);
    }

    public IngredienteDTO findByID(long id){
        Ingrediente ingrediente = repository.findById(id).orElse(null);
        Assert.notNull(ingrediente,"Lamentamos, nenhum ingrediente localizado com o ID informado.");
        return toIngredienteDTO(ingrediente);
    }

    public List<IngredienteDTO> getAll(){
        return repository.findAll().stream().map(this::toIngredienteDTO).toList();
    }

    @Transactional
      public IngredienteDTO post(IngredienteDTO ingredientes) {
        Assert.notNull(ingredientes.getNome(),"Por favor, insira um nome.");
        Assert.hasText(ingredientes.getNome(),"O nome é inválido.");
        Assert.notNull(ingredientes.getQuantidade(),"Por favor, digite a quantidade");
        return toIngredienteDTO(repository.save(toIngredienteEntidade(ingredientes)));

    }
    @Transactional
    public IngredienteDTO update(long id, IngredienteDTO ingredientes) {
        Assert.notNull(ingredientes.getId(),"Por favor, insira um ID.");
        Assert.hasText(ingredientes.getNome(),"O nome é inválido.");
        Assert.notNull(ingredientes.getQuantidade(),"Por favor, digite a quantidade");

        return toIngredienteDTO(repository.save(toIngredienteEntidade(ingredientes)));
    }

    public void delete(long id) {
        Ingrediente ingrediente = repository.findById(id).orElse(null);
        Assert.notNull(ingrediente,String.format("Nenhum ingrediente localizado com o id [%s]",id));

        repository.deleteById(id);
    }
}
