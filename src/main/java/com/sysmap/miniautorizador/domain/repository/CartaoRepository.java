package com.sysmap.miniautorizador.domain.repository;

import com.sysmap.miniautorizador.domain.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao,Long> {
}
