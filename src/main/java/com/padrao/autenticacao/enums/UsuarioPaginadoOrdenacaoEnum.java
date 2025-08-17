package com.padrao.autenticacao.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UsuarioPaginadoOrdenacaoEnum {

    NOME("nome"),
    EMAIL("email");

    private final String ordenacao;
}
