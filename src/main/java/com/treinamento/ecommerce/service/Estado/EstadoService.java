package com.treinamento.ecommerce.service.Estado;

import com.treinamento.ecommerce.domain.Cidade;
import com.treinamento.ecommerce.domain.Estado;
import com.treinamento.ecommerce.dto.Estado.InputEstadoDTO;
import com.treinamento.ecommerce.dto.Estado.OutPutEstadoDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EstadoService {
    List<OutPutEstadoDTO> findAll();
    OutPutEstadoDTO getById(long id) ;
    Estado create(Estado Estado);
    Estado updateName(String nome, long id);
    void removeCidade(Cidade cidade, long estadoId);
    void adicionarCidade(Cidade cidade, long estadoId);
    Estado checkIfExist(long id);
    void delete (long id);
    Page<OutPutEstadoDTO> findPage(int page, int size, String orderBy, String direction);
    Estado fromDTO(InputEstadoDTO EstadoDTO);
}
