package com.treinamento.ecommerce.service.Estado.impl;

import com.treinamento.ecommerce.domain.Cidade;
import com.treinamento.ecommerce.domain.Estado;
import com.treinamento.ecommerce.dto.Estado.InputEstadoDTO;
import com.treinamento.ecommerce.dto.Estado.OutPutEstadoDTO;
import com.treinamento.ecommerce.exception.serviceException.DataIntegrityException;
import com.treinamento.ecommerce.exception.serviceException.ObjectNotFoundException;
import com.treinamento.ecommerce.repository.EstadoRepository;
import com.treinamento.ecommerce.service.Estado.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class EstadoServiceImpl implements EstadoService {

    private final EstadoRepository estadoRepository;

    @Autowired
    public EstadoServiceImpl(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }


    @Override
    public List<OutPutEstadoDTO> findAll() {
        List<Estado> estadosList = estadoRepository.findAll();
        List<OutPutEstadoDTO> listConveted = new ArrayList<>();

        for (Estado e: estadosList) {
            listConveted.add(convertInDTO(e));
        }
        return listConveted;
    }

    @Override
    public OutPutEstadoDTO getById(long id) {
        Estado estado = checkIfExist(id);
        return convertInDTO(estado);
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
    public void removeCidade(Cidade cidade, long estadoId){
        Estado estado = estadoRepository.getById(estadoId);
        estado.getCidades().remove(cidade);
        estadoRepository.save(estado);
    }

    @Override
    public void adicionarCidade(Cidade cidade, long estadoId){
        Estado estado = estadoRepository.getById(estadoId);
        estado.getCidades().add(cidade);
        estadoRepository.save(estado);
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
    public Page<OutPutEstadoDTO> findPage(int page, int size, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        return new PageImpl<OutPutEstadoDTO>(findAll());
    }

    public OutPutEstadoDTO convertInDTO(Estado estado){

        OutPutEstadoDTO obj = new OutPutEstadoDTO();
        List<String> listEstados = new ArrayList<>();

        for (Cidade e: estado.getCidades()) {
            listEstados.add(e.getNome());
        }

        obj.setName(estado.getNome());
        obj.setCidades(listEstados);

        return obj;
    }

    @Override
    public Estado fromDTO(InputEstadoDTO estadoDTO) {
        return new Estado(null,estadoDTO.getNome());
    }
}
