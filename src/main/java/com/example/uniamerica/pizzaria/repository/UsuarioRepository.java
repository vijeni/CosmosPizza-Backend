package com.example.uniamerica.pizzaria.repository;

import com.example.uniamerica.pizzaria.entity.Role;
import com.example.uniamerica.pizzaria.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT p from Usuario p where p.role = :role")
    List<Usuario> findAllByTipo(@Param("role") Role role);

    @Query("from Usuario where cpf = :cpf")
    public List<Usuario> findByCpf(@Param("cpf") final String cpf);

    @Query("from Usuario where username = :username")
    public Usuario findByUsername(@Param("username") final String username);

}
