package com.treinamento.ecommerce.dto.Estado;

import java.util.List;

public class OutPutEstadoDTO {
    private String name;
    private List<String> cidades;

    public OutPutEstadoDTO(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCidades() {
        return cidades;
    }

    public void setCidades(List<String> cidades) {
        this.cidades = cidades;
    }
}
