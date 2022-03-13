package com.treinamento.ecommerce.resources;

import com.treinamento.ecommerce.domain.Cliente;
import com.treinamento.ecommerce.dto.ClienteDTO;
import com.treinamento.ecommerce.service.cliente.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
    private final ClienteService clienteService;

    @Autowired
    public ClienteResource (ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable long id){
        return ResponseEntity.ok().body(clienteService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Cliente>>findAllCliente(){
        return ResponseEntity.status(HttpStatus.FOUND).body(clienteService.findAll());
    }

    @RequestMapping(value ="/page", method = RequestMethod.GET)
    public ResponseEntity<Page<Cliente>> findPage(
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "24") int size,
            @RequestParam(value = "orderBy",defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction",defaultValue = "ASC") String direction){
        return ResponseEntity.status(HttpStatus.FOUND).body(clienteService.findPage(page, size, orderBy, direction));
    }

    @PostMapping("/create")
    public ResponseEntity<Void>create(@Valid @RequestBody ClienteDTO clienteDTO){
        Cliente cliente = clienteService.fromDTO(clienteDTO);
        cliente = clienteService.create(cliente);
        String urlObj =  ("localhost:8080/clientes/"+cliente.getId());
        return ResponseEntity.status(HttpStatus.CREATED).header("location", urlObj).build();
    }

    @PutMapping("/update")
    public ResponseEntity<Cliente>update(@RequestBody ClienteDTO clienteDTO, long id){
        Cliente cliente = clienteService.fromDTO(clienteDTO);
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.update(cliente,id));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String >delete(long id){
        clienteService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Sucesso");
    }
}
