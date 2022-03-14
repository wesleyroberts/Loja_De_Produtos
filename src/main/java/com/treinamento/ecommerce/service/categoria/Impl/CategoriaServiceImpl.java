package com.treinamento.ecommerce.service.categoria.Impl;

import com.treinamento.ecommerce.domain.Categoria;
import com.treinamento.ecommerce.dto.CategoriaDTO;
import com.treinamento.ecommerce.exception.serviceException.DataIntegrityException;
import com.treinamento.ecommerce.exception.serviceException.ObjectNotFoundException;
import com.treinamento.ecommerce.repository.CategoriaRepository;
import com.treinamento.ecommerce.service.categoria.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Categoria getById(long id)throws ObjectNotFoundException {
        return checkIfExist(id);
    }

    @Override
    public Categoria create(Categoria categoria) {
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria update(Categoria categoria, long id) throws ObjectNotFoundException {
        Categoria newCategoria = checkIfExist(id);
        updateData(newCategoria,categoria);
        return categoriaRepository.save(categoria)  ;
    }

    private void updateData(Categoria newCategoria, Categoria categoria) {
        newCategoria.setNome(categoria.getNome());
    }

    @Override
    public Categoria checkIfExist(long id) throws ObjectNotFoundException{
        return categoriaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+id + " ,tipo "+Categoria.class.getName()));
    }

    @Override
    public void delete(long id) {
        checkIfExist(id);
        try{
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivel excluir categoria que possui produtos.");
        }


    }

    @Override
    public Page<Categoria> findPage(int page, int size,String orderBy,String direction) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        return categoriaRepository.findAll(pageRequest);
    }

    @Override
    public Categoria fromDTO(CategoriaDTO objDTO){
        return new Categoria(objDTO.getId(),objDTO.getNome());
    }
}
