package com.treinamento.ecommerce.dto.cidade;

public class InputCidadeDTO {

    private String nome;
    private Long estadoId;

    public InputCidadeDTO(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Long estadoId) {
        this.estadoId = estadoId;
    }
}
