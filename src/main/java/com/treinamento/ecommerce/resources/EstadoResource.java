package com.treinamento.ecommerce.resources;

import com.treinamento.ecommerce.domain.Estado;
import com.treinamento.ecommerce.dto.Estado.InputEstadoDTO;

import com.treinamento.ecommerce.dto.Estado.OutPutEstadoDTO;
import com.treinamento.ecommerce.service.Estado.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoResource {
    private final com.treinamento.ecommerce.service.Estado.EstadoService estadoService;

    @Autowired
    public EstadoResource (EstadoService estadoService){
        this.estadoService = estadoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OutPutEstadoDTO> getEstadoById(@PathVariable long id){
        OutPutEstadoDTO estado = estadoService.getById(id);
        return ResponseEntity.ok().body(estado);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OutPutEstadoDTO>>findAllEstado(){
        List<OutPutEstadoDTO> list = estadoService.findAll();
        return ResponseEntity.status(HttpStatus.FOUND).body(list);
    }

    @RequestMapping(value ="/page", method = RequestMethod.GET)
    public ResponseEntity<Page<OutPutEstadoDTO>> findPage(
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "24") int size,
            @RequestParam(value = "orderBy",defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction",defaultValue = "ASC") String direction){
        Page<OutPutEstadoDTO> list = estadoService.findPage(page, size, orderBy, direction);
        return ResponseEntity.status(HttpStatus.FOUND).body(list);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@Valid @RequestBody InputEstadoDTO estadoDTO){
        Estado estado = estadoService.fromDTO(estadoDTO);
        estado = estadoService.create(estado);
        String urlObj =  ("localhost:8080/Estados/"+estado.getId());
        return ResponseEntity.status(HttpStatus.CREATED).header("location", urlObj).build();
    }

    @PutMapping("/update")
    public ResponseEntity<Estado>update(@RequestBody String name, long id){
        return ResponseEntity.status(HttpStatus.OK).body(estadoService.updateName(name,id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>delete(@PathVariable Long id){
        estadoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }
}
