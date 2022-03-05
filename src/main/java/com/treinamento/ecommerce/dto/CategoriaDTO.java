package com.treinamento.ecommerce.dto;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;


public class CategoriaDTO implements Serializable {
    private static final long serialVersionUID= 1L;

    private Long id;
    @NotEmpty(message = "Preenchimento obrigatorio")
    @Length(min=5, max=80, message= "O tamanho dever ser entre 5 e 80 caracteres")
    private String nome;

    public CategoriaDTO(){}

    public CategoriaDTO(String nome){
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
