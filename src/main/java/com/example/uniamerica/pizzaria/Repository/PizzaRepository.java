package com.example.uniamerica.pizzaria.Repository;

import com.example.uniamerica.pizzaria.Entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
