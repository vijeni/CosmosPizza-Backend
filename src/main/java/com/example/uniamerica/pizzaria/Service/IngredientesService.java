package com.example.uniamerica.pizzaria.Service;

import com.example.uniamerica.pizzaria.DTO.EnderecosDTO;
import com.example.uniamerica.pizzaria.DTO.IngredientesDTO;
import com.example.uniamerica.pizzaria.Entity.Ingredientes;
import com.example.uniamerica.pizzaria.Repository.IngredientesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class IngredientesService {
    @Autowired
    IngredientesRepository repository;

    public Ingredientes toIngredientes(IngredientesDTO ingredientesDTO){
        Ingredientes ingredientes = new Ingredientes();
        ingredientes.setNome(ingredientesDTO.getNome());
        ingredientes.setQuantidade(ingredientesDTO.getQuantidade());

        return ingredientes;
    }
    public IngredientesDTO toIngredientesDTO(Ingredientes ingredientes){
        IngredientesDTO ingredientesDTO = new IngredientesDTO();
        ingredientesDTO.setNome(ingredientes.getNome());
        ingredientesDTO.setQuantidade(ingredientes.getQuantidade());

        return ingredientesDTO;
    }



    @Transactional
      public IngredientesDTO post(IngredientesDTO ingredientes) {
        Assert.notNull(ingredientes.getNome(),"Por favor, insira um nome.");
        Assert.hasText(ingredientes.getNome(),"O nome é inválido.");
        Assert.notNull(ingredientes.getQuantidade(),"Por favor, digite a quantidade");
        return toIngredientesDTO(repository.save(toIngredientes(ingredientes)));

    }
}
