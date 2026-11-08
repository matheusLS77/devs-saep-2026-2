package com.senai.saep.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class MaterialDto {
    private Long id;

    @NotBlank(message = "Informe o nome do material")
    private String nome;

    @NotBlank(message = "Informe o código do material")
    private String codigo;

    @NotNull(message = "Informe a quantidade em estoque")
    @PositiveOrZero(message = "A quantidade não pode ser negativa")
    private Integer quantidadeEstoque;

    @NotNull(message = "Informe o estoque mínimo")
    @PositiveOrZero(message = "O estoque mínimo não pode ser negativo")
    private Integer estoqueMinimo;

    @NotNull(message = "Informe a categoria")
    private Long idCategoria;

    private String categoriaNome;

    public MaterialDto() {
    }

    public boolean estoqueBaixo() {
        if (quantidadeEstoque == null || estoqueMinimo == null) {
            return false;
        }

        return getQuantidadeEstoque() < getEstoqueMinimo();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Integer getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(Integer estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCategoriaNome() {
        return categoriaNome;
    }

    public void setCategoriaNome(String categoriaNome) {
        this.categoriaNome = categoriaNome;
    }
}
