package com.example.uniamerica.pizzaria.repository;

import com.example.uniamerica.pizzaria.entity.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente,Long> {


}
