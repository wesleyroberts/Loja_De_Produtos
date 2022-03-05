package com.treinamento.ecommerce.service.categoria;

import com.treinamento.ecommerce.domain.Categoria;

import com.treinamento.ecommerce.dto.CategoriaDTO;
import com.treinamento.ecommerce.exception.CategoriaNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoriaService {
    public List<Categoria> findAll();
    public Categoria getById(long id) throws CategoriaNotFoundException;
    public Categoria create(Categoria categoria);
    public Categoria update(Categoria categoria, long id) throws CategoriaNotFoundException;
    public Categoria checkIfExist(long id) throws CategoriaNotFoundException;
    public void delete (long id);
    public Page<Categoria> findPage(int page, int size,String orderBy,String direction);
    public Categoria fromDTO(CategoriaDTO objDTO);
}
