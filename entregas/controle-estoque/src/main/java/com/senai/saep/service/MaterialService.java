package com.senai.saep.service;

import com.senai.saep.dto.CategoriaDto;
import com.senai.saep.dto.MaterialDto;
import com.senai.saep.entity.CategoriaEntity;
import com.senai.saep.entity.MaterialEntity;
import com.senai.saep.repository.CategoriaRepository;
import com.senai.saep.repository.MaterialRepository;
import com.senai.saep.repository.MovimentacaoRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {
    private final MaterialRepository materialRepository;
    private final CategoriaRepository categoriaRepository;
    private final CategoriaService categoriaService;
    private final MovimentacaoRepository movimentacaoRepository;

    public MaterialService(
            MaterialRepository materialRepository,
            CategoriaRepository categoriaRepository,
            CategoriaService categoriaService,
            MovimentacaoRepository movimentacaoRepository) {

        this.materialRepository = materialRepository;
        this.categoriaRepository = categoriaRepository;
        this.categoriaService = categoriaService;
        this.movimentacaoRepository = movimentacaoRepository;
    }

    public void cadastrar(MaterialDto dto) {
        List<MaterialEntity> materiais = materialRepository.findAll();

        for (MaterialEntity material : materiais) {
            if (material.getNome().equals(dto.getNome())) {
                throw new IllegalArgumentException("Material já cadastrado");
            }
        }

        CategoriaEntity categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria nao encontrada"));

        MaterialEntity material = paraEntity(dto);
        material.setCategoria(categoria);

        materialRepository.save(material);
    }

    public List<MaterialDto> listar() {
        List<MaterialEntity> materiais = materialRepository.findAll();

        // lista ordenada
        for (int i = 0; i < materiais.size() - 1; i++) {
            for (int j = 0; j < materiais.size() - 1 - i; j++) {
                if (materiais.get(j).getNome()
                        .compareToIgnoreCase(materiais.get(j + 1).getNome()) > 0) {

                    MaterialEntity temp = materiais.get(j);

                    materiais.set(j, materiais.get(j + 1));
                    materiais.set(j + 1, temp);
                }
            }
        }

        List<MaterialDto> materialDtos = new ArrayList<>();
        for (MaterialEntity material : materiais) {
            materialDtos.add(paraDto(material));
        }

        return materialDtos;
    }

    public Page<MaterialDto> listarPaginado(int pagina) {
        Pageable pageable = PageRequest.of(pagina, 5,
                        Sort.by("nome").ascending());

        Page<MaterialEntity> materiais = materialRepository.findAll(pageable);

        List<MaterialDto> materialDtos = new ArrayList<>();
        for (MaterialEntity material : materiais) {
            materialDtos.add(paraDto(material));
        }

        return new PageImpl<>(materialDtos, pageable, materiais.getTotalElements());
    }

    public void atualizar(MaterialDto dto) {
        MaterialEntity material = materialRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Material não encontrado"));

        CategoriaEntity categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        material.setNome(dto.getNome());
        material.setQuantidadeEstoque(dto.getQuantidadeEstoque());
        material.setEstoqueMinimo(dto.getEstoqueMinimo());
        material.setCodigo(dto.getCodigo());
        material.setCategoria(categoria);

        materialRepository.save(material);
    }

    public void excluir(Long id) {
        if (!materialRepository.existsById(id)) {
            throw new IllegalArgumentException("Material não encontrado.");
        }

        if (movimentacaoRepository.existsByMaterial_Id(id)) {
            throw new IllegalArgumentException("Erro: Material possui movimentações vinculadas a ele.");
        }

        materialRepository.deleteById(id);
    }

    public MaterialDto obterMaterial(Long id) {
        Optional<MaterialEntity> materialOp = materialRepository.findById(id);

        MaterialDto material = new MaterialDto();

        if (materialOp.isPresent()) {
            material = paraDto(materialOp.get());
        }

        return material;
    }

    public List<MaterialDto> materiaisBusca(String material) {
        List<MaterialEntity> materiais = materialRepository.findByCodigoOrNomeContainingIgnoreCase(material, material);
        List<MaterialDto> materialDtos = new ArrayList<>();

        for (MaterialEntity materialEntity : materiais) {
            materialDtos.add(paraDto(materialEntity));
        }

        return materialDtos;
    }

    public int contarEstoqueBaixo() {
        int quantidade = 0;

        for (MaterialEntity material : materialRepository.findAll()) {
            if (material.getQuantidadeEstoque() < material.getEstoqueMinimo()) {
                quantidade++;
            }
        }

        return quantidade;
    }

    public MaterialEntity paraEntity(MaterialDto dto) {
        MaterialEntity entity = new MaterialEntity();

        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setCodigo(dto.getCodigo());
        entity.setQuantidadeEstoque(dto.getQuantidadeEstoque());
        entity.setEstoqueMinimo(dto.getEstoqueMinimo());

        CategoriaEntity categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        entity.setCategoria(categoria);

        return entity;
    }

    public MaterialDto paraDto(MaterialEntity entity) {
        MaterialDto dto = new MaterialDto();

        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCodigo(entity.getCodigo());
        dto.setQuantidadeEstoque(entity.getQuantidadeEstoque());
        dto.setEstoqueMinimo(entity.getEstoqueMinimo());
        dto.setIdCategoria(entity.getCategoria().getId());
        dto.setCategoriaNome(entity.getCategoria().getNome());

        return dto;
    }
}

