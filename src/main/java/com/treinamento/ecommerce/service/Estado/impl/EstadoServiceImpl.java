package com.treinamento.ecommerce.service.Estado.impl;

import com.treinamento.ecommerce.domain.Cidade;
import com.treinamento.ecommerce.domain.Estado;
import com.treinamento.ecommerce.dto.EstadoDTO;
import com.treinamento.ecommerce.exception.serviceException.DataIntegrityException;
import com.treinamento.ecommerce.exception.serviceException.ObjectNotFoundException;
import com.treinamento.ecommerce.repository.EstadoRepository;
import com.treinamento.ecommerce.service.Estado.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EstadoServiceImpl implements EstadoService {

    private final EstadoRepository estadoRepository;

    @Autowired
    public EstadoServiceImpl(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }


    @Override
    public List<Estado> findAll() {
        return estadoRepository.findAll();
    }

    @Override
    public Estado getById(long id) {
        return checkIfExist(id);
    }

    @Override
    public Estado create(Estado estado) {
        return estadoRepository.save(estado);
    }

    @Override
    public Estado updateName(String nome, long id) {
        Estado estado = checkIfExist(id);
        estado.setNome(nome);
        return estadoRepository.save(estado);
    }

    @Override
    public Estado removeCidade(Cidade cidade, long estadoId){
        Estado estado = estadoRepository.getById(estadoId);
        estado.getCidades().remove(cidade);
        return estadoRepository.save(estado);
    }

    @Override
    public Estado adicionarCidade(Cidade cidade, long estadoId){
        Estado estado = estadoRepository.getById(estadoId);
        estado.getCidades().add(cidade);
        return estadoRepository.save(estado);
    }

    @Override
    public Estado checkIfExist(long id) {
        return estadoRepository.
                findById(id).
                orElseThrow(() ->
                        new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " ,tipo " + Estado.class.getName()));
    }

    @Override
    public void delete(long id) {
        checkIfExist(id);
        try{
            estadoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Insdiponel a deleçao, pois o mesmo possui cidades relacionadas a ele.");
        }
    }

    @Override
    public Page<Estado> findPage(int page, int size, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        return estadoRepository.findAll(pageRequest);
    }

    @Override
    public Estado fromDTO(EstadoDTO estadoDTO) {
        return new Estado(null,estadoDTO.getNome());
    }
}
