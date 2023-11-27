package com.example.uniamerica.pizzaria.service;

import com.example.uniamerica.pizzaria.dto.UsuarioDTO;
import com.example.uniamerica.pizzaria.entity.Role;
import com.example.uniamerica.pizzaria.entity.Usuario;
import com.example.uniamerica.pizzaria.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
   private UsuarioRepository repository;

    private final ModelMapper modelMapper = new ModelMapper();

    public Usuario toUsuario(UsuarioDTO usuarioDTO){
        return modelMapper.map(usuarioDTO, Usuario.class);
    }

    public UsuarioDTO toUsuarioDTO(Usuario usuarioEntidade){
        return modelMapper.map(usuarioEntidade, UsuarioDTO.class);
    }
    public UsuarioDTO findById(String id){
        Usuario usuario = repository.findById(id).orElse(null);
        Assert.notNull(usuario, "Usuário não existe!");
        return toUsuarioDTO(usuario);
    }

    public List<UsuarioDTO> getAll(){
        return repository.findAll().stream().map(this::toUsuarioDTO).toList();
    }
    public List<UsuarioDTO>getAllAdm(){
        return repository.findAllByTipo(Role.ADMIN).stream().map(this::toUsuarioDTO).toList();
    }
    public List<UsuarioDTO>getAllFuncionarios(){
        return repository.findAllByTipo(Role.FUNCIONARIO).stream().map(this::toUsuarioDTO).toList();
    }

    @Transactional
    public Usuario usuarioUsername(String username){
        return repository.findByUsername(username);
    }

    @Transactional
    public UsuarioDTO post(UsuarioDTO usuario) {
        Assert.notNull(usuario.getUsername(),"Por favor, digite um username!");
        Assert.hasText(usuario.getUsername(),"Por favor, digite um nome válido!");

        final List<Usuario>usuariosCpf = this.repository.findByCpf(usuario.getCpf());
        Assert.isTrue(usuariosCpf.isEmpty(),"Cpf já cadastrado.");
        Assert.isNull(usuarioUsername(usuario.getUsername()),"Esse username não está mais disponível!");

        return toUsuarioDTO(repository.save(toUsuario(usuario)));
    }


    @Transactional
    public UsuarioDTO put(UsuarioDTO usuario, Long id) {
            Assert.notNull(usuario.getUsername(),"Por favor, digite um username!");
            Assert.hasText(usuario.getUsername(),"Por favor, digite um nome válido!");
            Assert.hasText(usuario.getUsername(),"Por favor, digite um telefone válido!");

            final List<Usuario>usuariosCpf = this.repository.findByCpf(usuario.getCpf());
            Assert.isTrue(usuariosCpf.isEmpty(),"Cpf já cadastrado.");

             Assert.isNull(usuarioUsername(usuario.getUsername()),"Esse username não está mais disponível!");

//            usuario.setPassword(encoder.encode(usuario.getPassword()));
            return toUsuarioDTO(repository.save(toUsuario(usuario)));
    }
    @Transactional
    public UsuarioDTO desativar(String id) {
        UsuarioDTO pessoa = findById(id);
        pessoa.desativar();
        return toUsuarioDTO(repository.save(toUsuario(pessoa)));

    }
    @Transactional
    public UsuarioDTO ativar(String id) {
        UsuarioDTO usuario = findById(id);
        usuario.ativar();
        return toUsuarioDTO(repository.save(toUsuario(usuario)));

    }

}
