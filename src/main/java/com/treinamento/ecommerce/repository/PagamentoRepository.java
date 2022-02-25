package com.treinamento.ecommerce.repository;

import com.treinamento.ecommerce.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento,Long> {
}
