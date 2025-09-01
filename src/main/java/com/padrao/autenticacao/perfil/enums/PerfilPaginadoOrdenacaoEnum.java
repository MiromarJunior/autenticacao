package com.padrao.autenticacao.perfil.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PerfilPaginadoOrdenacaoEnum {
    NOME("nome"),
    DESCRICAO("descricao");

    private final String ordenacao;
}
