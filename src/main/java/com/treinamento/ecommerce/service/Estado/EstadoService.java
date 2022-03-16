package com.treinamento.ecommerce.service.Estado;

import com.treinamento.ecommerce.domain.Cidade;
import com.treinamento.ecommerce.domain.Estado;
import com.treinamento.ecommerce.dto.EstadoDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EstadoService {
    List<Estado> findAll();
    Estado getById(long id) ;
    Estado create(Estado Estado);
    Estado updateName(String nome, long id);
    Estado removeCidade(Cidade cidade, long estadoId);
    Estado adicionarCidade(Cidade cidade, long estadoId);
    Estado checkIfExist(long id);
    void delete (long id);
    Page<Estado> findPage(int page, int size, String orderBy, String direction);
    Estado fromDTO(EstadoDTO EstadoDTO);
}
