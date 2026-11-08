package com.senai.saep.service;

import com.senai.saep.dto.MaterialDto;
import com.senai.saep.dto.MovimentacaoDto;
import com.senai.saep.entity.MaterialEntity;
import com.senai.saep.entity.MovimentacaoEntity;
import com.senai.saep.entity.UsuarioEntity;
import com.senai.saep.enums.TipoMovimentacao;
import com.senai.saep.repository.MaterialRepository;
import com.senai.saep.repository.MovimentacaoRepository;
import com.senai.saep.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovimentacaoService {
    private final MovimentacaoRepository movimentacaoRepository;
    private final MaterialRepository materialRepository;
    private final UsuarioRepository usuarioRepository;
    private final MaterialService materialService;

    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository, MaterialRepository materialRepository, UsuarioRepository usuarioRepository, MaterialService materialService) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.materialRepository = materialRepository;
        this.usuarioRepository = usuarioRepository;
        this.materialService = materialService;
    }

    public void cadastrar(MovimentacaoDto dto) {
        MaterialEntity materialEntity = materialRepository.findById(dto.getIdMaterial()).orElseThrow(() -> new RuntimeException("Material não encontrado"));

        if (TipoMovimentacao.ENTRADA == dto.getTipo()) {
            materialEntity.setQuantidadeEstoque(materialEntity.getQuantidadeEstoque() + dto.getQuantidade());
        } else if (TipoMovimentacao.SAIDA == dto.getTipo()) {
            if (materialEntity.getQuantidadeEstoque() < dto.getQuantidade()) {
                throw new IllegalArgumentException("Quantidade insuficiente em estoque");
            }

            materialEntity.setQuantidadeEstoque(materialEntity.getQuantidadeEstoque() - dto.getQuantidade());
        }

        materialRepository.save(materialEntity);

        MovimentacaoEntity entity = paraEntity(dto);
        entity.setDataMovimentacao(LocalDateTime.now());

        movimentacaoRepository.save(entity);
    }

    public void atualizar(MovimentacaoDto dto) {
        MovimentacaoEntity entity = movimentacaoRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Movimentação não encontrada"));

        movimentacaoRepository.save(entity);
    }

    public List<MovimentacaoDto> listar() {
        List<MovimentacaoEntity> movimentacoes = movimentacaoRepository.findAll();
        List<MovimentacaoDto> movimentacaoDtos = new ArrayList<>();

        for (MovimentacaoEntity entity : movimentacoes) {
            movimentacaoDtos.add(paraDto(entity));
        }

        return movimentacaoDtos;
    }

    public void excluir(Long id) {
        movimentacaoRepository.deleteById(id);
    }


    public MovimentacaoDto paraDto(MovimentacaoEntity entity) {
        MovimentacaoDto dto = new MovimentacaoDto();

        dto.setId(entity.getId());
        dto.setIdMaterial(entity.getMaterial().getId());
        dto.setNomeMaterial(entity.getMaterial().getNome());
        dto.setIdUsuario(entity.getUsuario().getId());
        dto.setNomeUsuario(entity.getUsuario().getLogin());
        dto.setTipo(entity.getTipo());
        dto.setQuantidade(entity.getQuantidade());
        dto.setDataMovimentacao(entity.getDataMovimentacao());

        return dto;
    }

    public MovimentacaoEntity paraEntity(MovimentacaoDto dto) {
        MovimentacaoEntity entity = new MovimentacaoEntity();

        MaterialEntity material = materialRepository.findById(dto.getIdMaterial()).orElseThrow(() -> new RuntimeException("Material não encontrado"));
        UsuarioEntity usuario = usuarioRepository.findById(dto.getIdUsuario()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        entity.setId(dto.getId());
        entity.setMaterial(material);
        entity.setUsuario(usuario);
        entity.setTipo(dto.getTipo());
        entity.setDataMovimentacao(dto.getDataMovimentacao());
        entity.setQuantidade(dto.getQuantidade());

        return entity;
    }
}
