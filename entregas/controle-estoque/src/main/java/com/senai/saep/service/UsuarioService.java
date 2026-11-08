package com.senai.saep.service;

import com.senai.saep.dto.UsuarioDto;
import com.senai.saep.entity.UsuarioEntity;
import com.senai.saep.enums.Papel;
import com.senai.saep.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

//    public UsuarioDto realizarLogin(UsuarioDto usuarioDto){
//        Optional<UsuarioEntity> usuarioOp = repository.findByLoginAndSenha(usuarioDto.getLogin(), usuarioDto.getSenha());
//
//        UsuarioDto dto = new UsuarioDto();
//
//        if (usuarioOp.isPresent()) {
//            dto = paraDto(usuarioOp.get());
//        }
//
//        return dto;
//    }

    public void cadastrar(UsuarioDto usuarioDto) {
        UsuarioEntity entity = paraEntity(usuarioDto);
        entity.setSenha(passwordEncoder.encode(usuarioDto.getSenha()));
        entity.setPapel(Papel.USUARIO);

        repository.save(entity);
    }

    public void atualizar(UsuarioDto usuarioDto) {
        UsuarioEntity usuario = repository.findById(usuarioDto.getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setLogin(usuarioDto.getLogin());
        usuario.setSenha(passwordEncoder.encode(usuarioDto.getSenha()));
        usuario.setPapel(Papel.USUARIO);

        repository.save(usuario);
    }

    public List<UsuarioDto> listar() {
        List<UsuarioEntity> usuarios = repository.findAll();
        List<UsuarioDto> usuarioDtos = new ArrayList<>();

        for (UsuarioEntity usuario : usuarios) {
            usuarioDtos.add(paraDto(usuario));
        }

        return usuarioDtos;
    }

    public UsuarioDto buscarPorLogin(String login) {
        UsuarioEntity usuario = repository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return paraDto(usuario);
    }

    private UsuarioDto paraDto(UsuarioEntity entity) {
        UsuarioDto dto = new UsuarioDto();

        dto.setId(entity.getId());
        dto.setLogin(entity.getLogin());

        return dto;
    }

    private UsuarioEntity paraEntity(UsuarioDto dto) {
        UsuarioEntity entity = new UsuarioEntity();

        entity.setLogin(dto.getLogin());
        entity.setSenha(dto.getSenha());

        return entity;
    }
}
