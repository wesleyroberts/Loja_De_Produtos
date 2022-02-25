package com.treinamento.ecommerce.repository;

import com.treinamento.ecommerce.domain.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido,Long> {
}
