package com.example.uniamerica.pizzaria.Service;

import com.example.uniamerica.pizzaria.DTO.SaborDTO;
import com.example.uniamerica.pizzaria.Entity.Sabor;
import com.example.uniamerica.pizzaria.Repository.SaborRepository;
import com.fasterxml.jackson.databind.jsontype.impl.AsExistingPropertyTypeSerializer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class SaborService {
    @Autowired
    private SaborRepository repository;
    private ModelMapper modelMapper = new ModelMapper();

    public Sabor toSaborEntidade(SaborDTO saborDTO){
        Sabor sabor = new Sabor();
        sabor.setDescricao(saborDTO.getDescricao());
        sabor.setNome(saborDTO.getNome());
        sabor.setIngredientes(saborDTO.getIngredientes());


        return sabor;
    }

    public SaborDTO toSaborDTO (Sabor saborEntidade){

       SaborDTO saborDTO = new SaborDTO();
       saborDTO.setDescricao(saborEntidade.getDescricao());
       saborDTO.setNome(saborEntidade.getNome());
       saborDTO.setIngredientes(saborEntidade.getIngredientes());
       saborDTO.setId(saborEntidade.getId());
        return  saborDTO;
    }



    public SaborDTO cadastrar(SaborDTO sabor){
        Assert.notNull(sabor.getIngredientes(),"Por favor, diga quais são os ingredientes!");
        Assert.notNull(sabor.getNome(),"Por favor, digite um nome para o sabor!");
//        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);

        return modelMapper.map(repository.save(toSaborEntidade(sabor)), SaborDTO.class);
    }


    public SaborDTO update(SaborDTO sabor, long id) {
        Assert.notNull(sabor.getIngredientes(),"Por favor, diga quais são os ingredientes!");
        Assert.notNull(sabor.getNome(),"Por favor, digite um nome para o sabor!");
        Assert.notNull(sabor.getId(),"Por favor, insira um ID!");

        return toSaborDTO(repository.save(toSaborEntidade(sabor)));
    }

    public void  delete(long id){
        Assert.notNull(repository.findById(id).orElse(null),String.format("Sabor com o id [%s] não localizado!", id));

        repository.deleteById(id);
    }
}
