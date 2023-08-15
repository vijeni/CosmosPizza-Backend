package com.example.uniamerica.pizzaria.Repository;

import com.example.uniamerica.pizzaria.Entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
