package com.treinamento.ecommerce.service.cidade;

import com.treinamento.ecommerce.domain.Cidade;
import com.treinamento.ecommerce.dto.Estado.OutPutEstadoDTO;
import com.treinamento.ecommerce.dto.cidade.InputCidadeDTO;
import com.treinamento.ecommerce.dto.cidade.OutputCidadeDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CidadeService {
    List<OutputCidadeDTO> findAll();
    OutputCidadeDTO getById(long id) ;
    Cidade create(Cidade cidade);
    Cidade update(Cidade cidade, long id) ;
    Cidade checkIfExist(long id);
    void delete (long id);
    Page<OutputCidadeDTO> findPage(int page, int size, String orderBy, String direction);
    Cidade fromDTO(InputCidadeDTO cidadeDTO);
}
