package com.example.uniamerica.pizzaria.Service;

import com.example.uniamerica.pizzaria.DTO.IngredienteDTO;
import com.example.uniamerica.pizzaria.Entity.Ingrediente;
import com.example.uniamerica.pizzaria.Repository.IngredienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class IngredientesService {
    @Autowired
    IngredienteRepository repository;

    public Ingrediente toIngredientes(IngredienteDTO ingredienteDTO){
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNome(ingredienteDTO.getNome());
        ingrediente.setQuantidade(ingredienteDTO.getQuantidade());

        return ingrediente;
    }
    public IngredienteDTO toIngredientesDTO(Ingrediente ingrediente){
        IngredienteDTO ingredienteDTO = new IngredienteDTO();
        ingredienteDTO.setNome(ingrediente.getNome());
        ingredienteDTO.setQuantidade(ingrediente.getQuantidade());

        return ingredienteDTO;
    }

    @Transactional
      public IngredienteDTO post(IngredienteDTO ingredientes) {
        Assert.notNull(ingredientes.getNome(),"Por favor, insira um nome.");
        Assert.hasText(ingredientes.getNome(),"O nome é inválido.");
        Assert.notNull(ingredientes.getQuantidade(),"Por favor, digite a quantidade");
        return toIngredientesDTO(repository.save(toIngredientes(ingredientes)));

    }
    @Transactional
    public IngredienteDTO update(long id, IngredienteDTO ingredientes) {
        Assert.notNull(ingredientes.getId(),"Por favor, insira um ID.");
        Assert.hasText(ingredientes.getNome(),"O nome é inválido.");
        Assert.notNull(ingredientes.getQuantidade(),"Por favor, digite a quantidade");

        return toIngredientesDTO(repository.save(toIngredientes(ingredientes)));
    }
}
