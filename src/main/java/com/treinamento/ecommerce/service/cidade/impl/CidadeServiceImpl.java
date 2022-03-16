package com.treinamento.ecommerce.service.cidade.impl;

import com.treinamento.ecommerce.domain.Cidade;
import com.treinamento.ecommerce.domain.Estado;
import com.treinamento.ecommerce.dto.Estado.OutPutEstadoDTO;
import com.treinamento.ecommerce.dto.cidade.InputCidadeDTO;
import com.treinamento.ecommerce.dto.cidade.OutputCidadeDTO;
import com.treinamento.ecommerce.exception.serviceException.DataIntegrityException;
import com.treinamento.ecommerce.exception.serviceException.ObjectNotFoundException;
import com.treinamento.ecommerce.repository.CidadeRepository;
import com.treinamento.ecommerce.service.Estado.EstadoService;
import com.treinamento.ecommerce.service.cidade.CidadeService;
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
public class CidadeServiceImpl implements CidadeService {
    private final CidadeRepository cidadeRepository;
    private final EstadoService estadoServive;

    @Autowired
    public CidadeServiceImpl(CidadeRepository cidadeRepository, EstadoService estadoServive){
       this.cidadeRepository = cidadeRepository;
        this.estadoServive = estadoServive;
    }

    @Override
    public List<OutputCidadeDTO> findAll() {
        List<Cidade> cidadeList = cidadeRepository.findAll();
        List<OutputCidadeDTO> listDTO = new ArrayList<>();
        for (Cidade cidade:cidadeList) {
            listDTO.add(convertInDTO(cidade));
        }
        return listDTO;
    }


    @Override
    public OutputCidadeDTO getById(long id) {
        return convertInDTO(checkIfExist(id));
    }

    @Override
    public Cidade create(Cidade cidade) {
        cidade = cidadeRepository.save(cidade);
        estadoServive.adicionarCidade(cidade,cidade.getEstado().getId());
        return checkIfExist(cidade.getId());
    }

    @Override
    public Cidade update(Cidade cidade, long id) {
        return null;
    }

    @Override
    public Cidade checkIfExist(long id) throws ObjectNotFoundException {
        return cidadeRepository.
                findById(id).
                orElseThrow(() ->
                        new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " ,tipo " + Cidade.class.getName()));
    }

    @Override
    public void delete(long id) {
        Cidade cidade = checkIfExist(id);
        try{
            estadoServive.removeCidade(cidade, cidade.getEstado().getId());
            cidadeRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("erro ao tentar repover a cidade");
        }
    }

    @Override
    public Page<OutputCidadeDTO> findPage(int page, int size, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        return new PageImpl<OutputCidadeDTO>(findAll());
    }

    @Override
    public Cidade fromDTO(InputCidadeDTO cidadeDTO) {
        Estado estado = estadoServive.checkIfExist(cidadeDTO.getEstadoId());
        return new Cidade(null,cidadeDTO.getNome(),estado);
    }

    public OutputCidadeDTO convertInDTO(Cidade cidade){
        OutputCidadeDTO obj = new OutputCidadeDTO();
        obj.setEstado(cidade.getEstado().getNome());
        obj.setNome(cidade.getNome());

        return obj;
    }
}
