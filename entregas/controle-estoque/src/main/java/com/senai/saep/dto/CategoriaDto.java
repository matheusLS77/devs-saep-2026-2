package com.senai.saep.dto;

import jakarta.validation.constraints.NotBlank;

public class CategoriaDto {
    private Long id;

    @NotBlank(message = "Informe o nome da categoria")
    private String nome;

    public CategoriaDto() {
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
