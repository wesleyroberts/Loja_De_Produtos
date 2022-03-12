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

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> getCategoriaById(@PathVariable long id){
        Categoria categoria = categoriaService.getById(id);
        CategoriaDTO categoriaDTO = new CategoriaDTO(categoria);
        return ResponseEntity.ok().body(categoriaDTO);
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
    public ResponseEntity<Void> create(@Valid @RequestBody CategoriaDTO categoriaDTO){
        Categoria categoria = new Categoria();
        categoria.setNome(categoriaDTO.getNome());
        categoria = categoriaService.create(categoria);
        String urlObj =  ("localhost:8080/categorias/"+categoria.getId());
        return ResponseEntity.status(HttpStatus.CREATED).header("location", urlObj).build();
    }

    @PutMapping("/update")
    public ResponseEntity<Categoria>update(@RequestBody CategoriaDTO categoriaDTO, long id){
        Categoria categoria = categoriaService.fromDTO(categoriaDTO);
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.update(categoria,id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>delete(@PathVariable Long id){
        categoriaService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }
}
