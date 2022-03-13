package com.treinamento.ecommerce.service.cliente.Impl;

import com.treinamento.ecommerce.domain.Cliente;
import com.treinamento.ecommerce.dto.ClienteDTO;
import com.treinamento.ecommerce.exception.serviceException.DataIntegrityException;
import com.treinamento.ecommerce.exception.serviceException.ObjectNotFoundException;
import com.treinamento.ecommerce.repository.ClienteRepository;
import com.treinamento.ecommerce.repository.EnderecoRepository;
import com.treinamento.ecommerce.service.cliente.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository, EnderecoRepository enderecoRepository){
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
    }
    
    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente getById(long id)throws ObjectNotFoundException {
       return checkIfExist(id);
    }

    @Override
    public Cliente create(Cliente cliente)
    {
        cliente.setId(null);
        cliente = clienteRepository.save(cliente);
        if(!cliente.getEnderecos().isEmpty()){
            enderecoRepository.saveAll(cliente.getEnderecos());
        }
        return null;
    }

    @Override
    public Cliente update(Cliente newData, long id)throws ObjectNotFoundException{
        Cliente cliente = checkIfExist(id);
        updateData(newData, cliente);
        return clienteRepository.save(cliente);
    }

    @Override
    public void delete(long id) {
        checkIfExist(id);
        try{
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("cliente erro.");
        }
    }

    @Override
    public Page<Cliente> findPage(int page, int size, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    @Override
    public Cliente fromDTO(ClienteDTO clienteDTO) {
        return new Cliente(clienteDTO.getId(),
                clienteDTO.getNome(),
                clienteDTO.getEmail(),
                clienteDTO.getCpfOuCnpj(),
                1
        );
    }

    @Override
    public Cliente checkIfExist(long id) throws ObjectNotFoundException {
        return clienteRepository.
                findById(id).
                orElseThrow(() ->
                        new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " ,tipo " + Cliente.class.getName()));
    }

    private void updateData(Cliente newData, Cliente cliente){
        cliente.setNome(newData.getNome());
        cliente.setEmail(newData.getEmail());
    }
}
