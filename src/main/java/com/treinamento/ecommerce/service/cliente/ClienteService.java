package com.treinamento.ecommerce.service.cliente;
import com.treinamento.ecommerce.domain.Cliente;
import com.treinamento.ecommerce.dto.ClienteDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClienteService {
    public List<Cliente> findAll();
    public Cliente getById(long id) ;
    public Cliente create(Cliente cliente);
    public Cliente update(Cliente cliente, long id) ;
    public Cliente checkIfExist(long id);
    public void delete (long id);
    public Page<Cliente> findPage(int page, int size, String orderBy, String direction);
    public Cliente fromDTO(ClienteDTO objDTO);
}
