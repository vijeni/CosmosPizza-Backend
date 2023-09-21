package com.example.uniamerica.pizzaria.service;

import com.example.uniamerica.pizzaria.dto.PizzaDTO;
import com.example.uniamerica.pizzaria.dto.SaborDTO;
import com.example.uniamerica.pizzaria.dto.TamanhoDTO;
import com.example.uniamerica.pizzaria.entity.Pizza;
import com.example.uniamerica.pizzaria.repository.PizzaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository repository;
    @Autowired
    private SaborService saborService;
    @Autowired
    private TamanhoService tamanhoService;

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
            String tamanho = tamanhoDTO.getNome();
            Assert.isTrue(qntdSabores <= qntdMaximaSabores, String.format("A pizza tamanho %s deve ter no máximo %s sabor(es)", tamanho, qntdMaximaSabores));
            for (SaborDTO sabor :
                    pizza.getSabores()) {
                saborService.findById(sabor.getId());
            }
        }
    }

    public Double valorPizzas(List<PizzaDTO> pizzas) {
        Double valor = (double) 0;
        for (PizzaDTO pizza:
             pizzas) {
            TamanhoDTO tamanhoDTO = tamanhoService.findById(pizza.getTamanho().getId());
            valor += tamanhoDTO.getValor();
        }
        return valor;
    }
}
