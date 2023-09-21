package com.example.uniamerica.pizzaria.repository;

import com.example.uniamerica.pizzaria.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Query("from Produto where nome like :nome")
    List<Produto> findByNameLike(@Param("nome") final String nome);
}