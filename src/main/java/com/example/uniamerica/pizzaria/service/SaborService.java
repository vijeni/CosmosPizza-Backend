package com.example.uniamerica.pizzaria.service;

import com.example.uniamerica.pizzaria.dto.SaborDTO;
import com.example.uniamerica.pizzaria.entity.Sabor;
import com.example.uniamerica.pizzaria.repository.SaborRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class SaborService {
    @Autowired
    private SaborRepository repository;
    private final ModelMapper modelMapper = new ModelMapper();

    public Sabor toSabor(SaborDTO saborDTO){
        return modelMapper.map(saborDTO,Sabor.class);
    }

    public SaborDTO toSaborDTO(Sabor saborEntidade){
        return modelMapper.map(saborEntidade, SaborDTO.class);
    }

    public SaborDTO findById(long id){
        Sabor sabor = repository.findById(id).orElse(null);
        Assert.notNull(sabor,String.format("Lamentamos, não localizamos nenhum sabor com o id [%s]",id));

        return toSaborDTO(sabor);
    }

    public List<SaborDTO>getAll(){
        return repository.findAll().stream().map(this::toSaborDTO).toList();
    }

    public SaborDTO cadastrar(SaborDTO saborDTO){
        Assert.notNull(saborDTO.getIngredientes(),"Por favor, diga quais são os ingredientes!");
        Assert.notNull(saborDTO.getNome(),"Por favor, digite um nome para o saborDTO!");

        return toSaborDTO(repository.save(toSabor(saborDTO)));
    }



    public SaborDTO update(SaborDTO sabor, long id) {
        Assert.notNull(repository.findById(id).orElse(null),"Lamento, nenhum sabor localizado com esse ID.");
        Assert.notNull(sabor.getIngredientes(),"Por favor, diga quais são os ingredientes!");
        Assert.notNull(sabor.getNome(),"Por favor, digite um nome para o sabor!");
        Assert.notNull(sabor.getId(),"Por favor, insira um ID!");

        return modelMapper.map(repository.save(modelMapper.map(sabor, Sabor.class)),SaborDTO.class);
    }

    public SaborDTO desativar(long id){
        SaborDTO saborDTO = findById(id);
        saborDTO.desativar();
        return toSaborDTO(repository.save(toSabor(saborDTO)));
    }
}
