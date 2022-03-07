package com.treinamento.ecommerce.resources;

import com.treinamento.ecommerce.domain.Categoria;
import com.treinamento.ecommerce.dto.CategoriaDTO;
import com.treinamento.ecommerce.service.categoria.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaResource (CategoriaService categoriaService){
        this.categoriaService = categoriaService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable long id){
        return ResponseEntity.ok().body(categoriaService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoriaDTO>>findAllCategoria(){
        List<Categoria> list = categoriaService.findAll();
        List<CategoriaDTO> listDTO = list.stream().map(CategoriaDTO::new).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.FOUND).body(listDTO);
    }

    @RequestMapping(value ="/page", method = RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDTO>> findPage(
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "24") int size,
            @RequestParam(value = "orderBy",defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction",defaultValue = "ASC") String direction){
        Page<Categoria> list = categoriaService.findPage(page, size, orderBy, direction);
        Page<CategoriaDTO> listDto = list.map(CategoriaDTO::new);
        return ResponseEntity.status(HttpStatus.FOUND).body(listDto);
    }

    @PostMapping("/create")
    public ResponseEntity<Void>create(@Valid @RequestBody CategoriaDTO categoriaDTO){
        Categoria categoria = categoriaService.fromDTO(categoriaDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/update")
    public ResponseEntity<Categoria>update(@RequestBody Categoria categoria, long id){
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.update(categoria,id));
    }


}
