package com.sysmap.miniautorizador.domain.repository;

import com.sysmap.miniautorizador.domain.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao,Long> {
     Optional<Cartao> findCartaoByNumeroCartao(String numeroCartao);
}
