package com.example.uniamerica.pizzaria.repository;

import com.example.uniamerica.pizzaria.entity.Tamanho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TamanhoRepository extends JpaRepository<Tamanho, Long> {
    @Query("from Tamanho where nome like :tamanho")
    List<Tamanho> findByNameLike(@Param("tamanho") String tamanho);
}
