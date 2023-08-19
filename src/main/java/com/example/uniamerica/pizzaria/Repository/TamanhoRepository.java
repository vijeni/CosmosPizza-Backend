package com.example.uniamerica.pizzaria.Repository;

import com.example.uniamerica.pizzaria.Entity.Tamanho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface TamanhoRepository extends JpaRepository<Tamanho, Long> {
    @Query("from Tamanho where tamanho like :tamanho")
    List<Tamanho> findByNameLike(@Param("tamanho") String tamanho);
}
