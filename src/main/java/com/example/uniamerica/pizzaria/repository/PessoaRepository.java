package com.example.uniamerica.pizzaria.repository;

import com.example.uniamerica.pizzaria.entity.Pessoa;
import com.example.uniamerica.pizzaria.entity.Produto;
import com.example.uniamerica.pizzaria.entity.TipoPessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    @Query("SELECT p from Pessoa p where p.tipoPessoa = :tipoPessoa")
    List<Pessoa> findAllByTipo(@Param("tipoPessoa") TipoPessoa tipoPessoa);

    @Query("from Pessoa where cpf = :cpf")
    public List<Pessoa> findByCpf(@Param("cpf") final String cpf);
}
