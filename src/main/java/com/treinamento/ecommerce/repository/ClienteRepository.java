package com.treinamento.ecommerce.repository;

import com.treinamento.ecommerce.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
