package com.example.uniamerica.pizzaria.Repository;

import com.example.uniamerica.pizzaria.Entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
