package com.senai.saep.repository;

import com.senai.saep.entity.MaterialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<MaterialEntity, Long> {
    List<MaterialEntity> findByCodigoOrNomeContainingIgnoreCase(String codigo, String nome);
}
