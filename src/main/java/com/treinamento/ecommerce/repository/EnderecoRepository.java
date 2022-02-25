package com.treinamento.ecommerce.repository;

import com.treinamento.ecommerce.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
