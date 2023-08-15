package com.example.uniamerica.pizzaria.Service;

import com.example.uniamerica.pizzaria.DTO.IngredientDTO;
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

    public Ingrediente toIngredientes(IngredientDTO ingredientDTO){
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNome(ingredientDTO.getNome());
        ingrediente.setQuantidade(ingredientDTO.getQuantidade());

        return ingrediente;
    }
    public IngredientDTO toIngredientesDTO(Ingrediente ingrediente){
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setNome(ingrediente.getNome());
        ingredientDTO.setQuantidade(ingrediente.getQuantidade());

        return ingredientDTO;
    }



    @Transactional
      public IngredientDTO post(IngredientDTO ingredientes) {
        Assert.notNull(ingredientes.getNome(),"Por favor, insira um nome.");
        Assert.hasText(ingredientes.getNome(),"O nome é inválido.");
        Assert.notNull(ingredientes.getQuantidade(),"Por favor, digite a quantidade");
        return toIngredientesDTO(repository.save(toIngredientes(ingredientes)));

    }
}
