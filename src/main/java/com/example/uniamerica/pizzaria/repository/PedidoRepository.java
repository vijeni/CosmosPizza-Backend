package com.example.uniamerica.pizzaria.repository;

import com.example.uniamerica.pizzaria.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
