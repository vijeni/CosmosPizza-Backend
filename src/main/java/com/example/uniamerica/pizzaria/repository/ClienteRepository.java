package com.example.uniamerica.pizzaria.repository;

import com.example.uniamerica.pizzaria.entity.Cliente;
import com.example.uniamerica.pizzaria.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("SELECT p from Cliente p where p.tipoPessoa = :tipoPessoa")
    List<Cliente> findAllByTipo(@Param("tipoPessoa") Role role);

    @Query("from Cliente where cpf = :cpf")
    public List<Cliente> findByCpf(@Param("cpf") final String cpf);
}
