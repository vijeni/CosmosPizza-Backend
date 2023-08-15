package com.example.uniamerica.pizzaria.Repository;

import com.example.uniamerica.pizzaria.Entity.Sabor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface SaborRepository extends JpaRepository<Sabor, Long> {
}
