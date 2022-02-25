package com.treinamento.ecommerce.repository;

import com.treinamento.ecommerce.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {
}
