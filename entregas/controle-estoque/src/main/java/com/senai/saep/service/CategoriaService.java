package com.senai.saep.service;

import com.senai.saep.dto.CategoriaDto;
import com.senai.saep.dto.MaterialDto;
import com.senai.saep.entity.CategoriaEntity;
import com.senai.saep.entity.MaterialEntity;
import com.senai.saep.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public void cadastrar(CategoriaDto dto) {
        List<CategoriaDto> categorias = listar();

        for (CategoriaDto categoria : categorias) {
            if (categoria.getNome().equals(dto.getNome())) {
                throw new IllegalArgumentException("Categoria já cadastrada");
            }
        }

        CategoriaEntity categoria = paraEntity(dto);

        categoriaRepository.save(categoria);
    }

    public void atualizar(CategoriaDto dto) {
        CategoriaEntity categoria = categoriaRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        categoria.setNome(dto.getNome());

        categoriaRepository.save(categoria);
    }

    public void excluir(Long id) {
        categoriaRepository.deleteById(id);
    }

    public List<CategoriaDto> listar() {
        List<CategoriaEntity> categorias = categoriaRepository.findAll();
        List<CategoriaDto> categoriaDtos = new ArrayList<>();

        for (CategoriaEntity categoria : categorias) {
            categoriaDtos.add(paraDto(categoria));
        }

        return categoriaDtos;
    }

    public List<CategoriaDto> categoriaBusca(String categoria) {
        List<CategoriaEntity> categorias = categoriaRepository.findByNomeContainingIgnoreCase(categoria);
        List<CategoriaDto> categoriaDtos = new ArrayList<>();

        for (CategoriaEntity categoriaEntity : categorias) {
            categoriaDtos.add(paraDto(categoriaEntity));
        }

        return categoriaDtos;
    }

    public CategoriaDto obterCategoria(Long id) {
        Optional<CategoriaEntity> categoriaOp = categoriaRepository.findById(id);

        CategoriaDto categoria = new CategoriaDto();

        if (categoriaOp.isPresent()) {
            categoria = paraDto(categoriaOp.get());
        }

        return categoria;
    }

    public CategoriaEntity paraEntity(CategoriaDto dto) {
        CategoriaEntity entity = new CategoriaEntity();

        entity.setId(dto.getId());
        entity.setNome(dto.getNome());

        return entity;
    }

    public CategoriaDto paraDto(CategoriaEntity entity) {
        CategoriaDto dto = new CategoriaDto();

        dto.setId(entity.getId());
        dto.setNome(entity.getNome());

        return dto;
    }
}