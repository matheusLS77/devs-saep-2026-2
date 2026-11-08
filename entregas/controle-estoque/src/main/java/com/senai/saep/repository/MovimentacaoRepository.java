package com.senai.saep.repository;

import com.senai.saep.entity.MovimentacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentacaoRepository extends JpaRepository<MovimentacaoEntity, Long> {
    boolean existsByMaterial_Id(Long idMaterial);
}
