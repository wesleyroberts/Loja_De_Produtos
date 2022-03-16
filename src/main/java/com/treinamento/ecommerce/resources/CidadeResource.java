package com.treinamento.ecommerce.resources;
import com.treinamento.ecommerce.domain.Cidade;
import com.treinamento.ecommerce.dto.Estado.OutPutEstadoDTO;
import com.treinamento.ecommerce.dto.cidade.InputCidadeDTO;
import com.treinamento.ecommerce.dto.cidade.OutputCidadeDTO;
import com.treinamento.ecommerce.service.cidade.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {
    private final CidadeService cidadeService;

    @Autowired
    public CidadeResource(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OutputCidadeDTO> getCidadeById(@PathVariable long id){
        OutputCidadeDTO cidade = cidadeService.getById(id);
        return ResponseEntity.ok().body(cidade);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OutputCidadeDTO>>findAllCidade(){
        List<OutputCidadeDTO> list = cidadeService.findAll();
        return ResponseEntity.status(HttpStatus.FOUND).body(list);
    }

    @RequestMapping(value ="/page", method = RequestMethod.GET)
    public ResponseEntity<Page<OutputCidadeDTO>> findPage(
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "24") int size,
            @RequestParam(value = "orderBy",defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction",defaultValue = "ASC") String direction){
        Page<OutputCidadeDTO> list = cidadeService.findPage(page, size, orderBy, direction);
        return ResponseEntity.status(HttpStatus.FOUND).body(list);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@Valid @RequestBody InputCidadeDTO cidadeDTO){
        Cidade cidade = cidadeService.fromDTO(cidadeDTO);
        cidade = cidadeService.create(cidade);
        String urlObj =  ("localhost:8080/Cidades/"+cidade.getId());
        return ResponseEntity.status(HttpStatus.CREATED).header("location", urlObj).build();
    }

    @PutMapping("/update")
    public ResponseEntity<Cidade>update(@RequestBody InputCidadeDTO cidadeDTO, long id){
        Cidade cidade = cidadeService.fromDTO(cidadeDTO);
        return ResponseEntity.status(HttpStatus.OK).body(cidadeService.update(cidade,id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>delete(@PathVariable Long id){
        cidadeService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }
}
