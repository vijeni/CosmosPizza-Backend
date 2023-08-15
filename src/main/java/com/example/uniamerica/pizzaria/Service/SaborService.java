package com.example.uniamerica.pizzaria.Service;

import com.example.uniamerica.pizzaria.DTO.SaborDTO;
import com.example.uniamerica.pizzaria.Entity.Sabor;
import com.example.uniamerica.pizzaria.Repository.SaborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class SaborService {
    @Autowired
    private SaborRepository repository;

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

        return toSaborDTO(repository.save(toSaborEntidade(sabor)));
    }


}
