package com.treinamento.ecommerce.exception;

public class CategoriaNotFoundException extends RuntimeException{
    public CategoriaNotFoundException() {
        super("Entidade nao encontrada");
    }
}
