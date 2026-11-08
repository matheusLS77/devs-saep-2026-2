package com.senai.saep.repository;

import com.senai.saep.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
//    Optional<UsuarioEntity> findByLoginAndSenha(String login, String senha);
    Optional<UsuarioEntity> findByLogin(String login);
}
