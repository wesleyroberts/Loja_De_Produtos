package com.treinamento.ecommerce.repository;

import com.treinamento.ecommerce.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
