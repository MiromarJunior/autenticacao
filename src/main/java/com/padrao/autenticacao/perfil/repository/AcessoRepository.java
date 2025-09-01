package com.padrao.autenticacao.perfil.repository;

import com.padrao.autenticacao.perfil.model.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcessoRepository extends JpaRepository<Acesso, Long> {
}
