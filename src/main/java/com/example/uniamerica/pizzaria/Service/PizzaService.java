package com.example.uniamerica.pizzaria.Service;

import com.example.uniamerica.pizzaria.DTO.PizzaDTO;
import com.example.uniamerica.pizzaria.DTO.TamanhoDTO;
import com.example.uniamerica.pizzaria.Entity.Pizza;
import com.example.uniamerica.pizzaria.Entity.Sabor;
import com.example.uniamerica.pizzaria.Entity.Tamanho;
import com.example.uniamerica.pizzaria.Repository.PizzaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class PizzaService {
    @Autowired
    PizzaRepository repository;
    @Autowired
    SaborService saborService;
    @Autowired
    TamanhoService tamanhoService;

    private final ModelMapper modelMapper = new ModelMapper();

    public Pizza toPizza(PizzaDTO pizzaDTO){
        return modelMapper.map(pizzaDTO, Pizza.class);
    }
    public PizzaDTO toPizzaDTO(Pizza pizza){
        return modelMapper.map(pizza, PizzaDTO.class);
    }

    public PizzaDTO findById(Long id) {
        Pizza pizza = repository.findById(id).orElse(null);
        Assert.notNull(pizza, "Pizza com ID %s não existe");
        return toPizzaDTO(pizza);
    }

    public List<PizzaDTO> getAll() {
        return repository.findAll().stream().map(this::toPizzaDTO).toList();
    }

    @Transactional
    public PizzaDTO cadastrar(PizzaDTO pizzaDTO) {
        return toPizzaDTO(repository.save(toPizza(pizzaDTO)));
    }

    public PizzaDTO editar(Long id, PizzaDTO pizzaDTO) {
        Assert.notNull(repository.findById(id).orElse(null), String.format("Pizza com ID %s não existe!", id));
        return toPizzaDTO(repository.save(toPizza(pizzaDTO)));
    }

    public void deletar(Long id) {
        Assert.notNull(repository.findById(id).orElse(null), String.format("Pizza com ID %s não existe!", id));
        repository.deleteById(id);
    }

    public void validarPizzas(List<PizzaDTO> pizzas) {
        for (PizzaDTO pizza:
             pizzas) {
            TamanhoDTO tamanhoDTO = tamanhoService.findById(pizza.getTamanho().getId());
            int qntdMaximaSabores = tamanhoDTO.getMaximoSabores();
            int qntdSabores = pizza.getSabores().size();
            String tamanho = tamanhoDTO.getTamanho();
            Assert.isTrue(qntdSabores <= qntdMaximaSabores, String.format("A pizza tamanho %s deve ter no máximo %s sabor(es)", tamanho, qntdMaximaSabores));
            for (Sabor sabor :
                    pizza.getSabores()) {
                saborService.findById(sabor.getId());
            }
        }
    }
}
