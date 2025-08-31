package com.padrao.autenticacao.usuario.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UsuarioPaginadoOrdenacaoEnum {

    NOME("nome"),
    EMAIL("email");

    private final String ordenacao;
}
