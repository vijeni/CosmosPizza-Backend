package com.example.uniamerica.pizzaria.Repository;

import com.example.uniamerica.pizzaria.Entity.Enderecos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecosRepository extends JpaRepository<Enderecos, Long> {




}
