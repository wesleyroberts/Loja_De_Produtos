package com.treinamento.ecommerce.service.categoria.Impl;

import com.treinamento.ecommerce.domain.Categoria;
import com.treinamento.ecommerce.exception.CategoriaNotFoundException;
import com.treinamento.ecommerce.repository.CategoriaRepository;
import com.treinamento.ecommerce.service.categoria.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaServiceImpl(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria getById(long id)throws CategoriaNotFoundException {
        return categoriaRepository.findById(id).get();
    }

    @Override
    public Categoria create(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria update(Categoria categoria, long id) throws CategoriaNotFoundException {
        return categoriaRepository.save(categoria)  ;
    }

    @Override
    public Categoria checkIfExist(long id) throws CategoriaNotFoundException{
        return categoriaRepository.findById(id).orElseThrow(CategoriaNotFoundException::new);
    }

    @Override
    public void delete(long id) {
        categoriaRepository.deleteById(id);
    }


    public Page<Categoria> findPage(int page, int size,String orderBy,String direction) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        return categoriaRepository.findAll(pageRequest);
    }
}
