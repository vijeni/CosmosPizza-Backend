package com.example.uniamerica.pizzaria.repository;

import com.example.uniamerica.pizzaria.entity.Sabor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaborRepository extends JpaRepository<Sabor, Long> {
}
