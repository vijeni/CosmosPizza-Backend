package com.example.uniamerica.pizzaria.repository;

import com.example.uniamerica.pizzaria.entity.Cliente;
import com.example.uniamerica.pizzaria.entity.Role;
import com.example.uniamerica.pizzaria.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Long, Usuario> {
    @Query("SELECT p from Usuario p where p.role = :role")
    List<Cliente> findAllByTipo(@Param("role") Role role);

    @Query("from Usuario where cpf = :cpf")
    public List<Cliente> findByCpf(@Param("cpf") final String cpf);


}
