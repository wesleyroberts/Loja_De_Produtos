package com.treinamento.ecommerce.dto;
import com.treinamento.ecommerce.domain.Cliente;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class ClienteDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @NotEmpty(message = "Peenchimento obrigatorio")
    @Length(min = 3, message = "O tamanho deve ser entre 3 e 120 caracteres")
    private String nome;
    private Long id;
    @Email(message = "Email invalido")
    @NotEmpty(message = "Peenchimento obrigatorio")
    private String email;
    private String cpfOuCnpj;

    public ClienteDTO (){}

    public ClienteDTO(Cliente cliente){
        id = cliente.getId();
        nome = cliente.getNome();
        email = cliente.getEmail();
        cpfOuCnpj = cliente.getCpfOuCnpj();
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }
}
