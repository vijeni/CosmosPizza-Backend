package com.example.uniamerica.pizzaria.Service;

import com.example.uniamerica.pizzaria.DTO.PizzaDTO;
import com.example.uniamerica.pizzaria.Entity.Pizza;
import com.example.uniamerica.pizzaria.Entity.Produto;
import com.example.uniamerica.pizzaria.Entity.Tamanho;
import com.example.uniamerica.pizzaria.Repository.PizzaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class PizzaService {
    @Autowired
    PizzaRepository repository;

    private ModelMapper modelMapper = new ModelMapper();

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
            Tamanho tamanho = pizza.getTamanho();
            int qntdSabores = pizza.getSabores().size();
            switch (tamanho) {
                case PEQUENA -> Assert.isTrue(qntdSabores == 1, "A pizza pequena deve conter apenas 1 sabor");
                case MEDIA -> Assert.isTrue(qntdSabores <= 2, "A pizza pequena deve conter até 2 sabores");
                case GRANDE -> Assert.isTrue(qntdSabores <= 3, "A pizza pequena deve conter até 3 sabores");
                case GIGANTE -> Assert.isTrue(qntdSabores <= 4, "A pizza pequena deve conter até 4 sabores");
            }
        }
    }
}
