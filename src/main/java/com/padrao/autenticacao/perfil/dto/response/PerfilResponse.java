package com.padrao.autenticacao.perfil.dto.response;

import lombok.Data;

@Data
public class PerfilResponse {

    private Long id;

    private String nome;

    private String descricao;

    private Boolean status;
}
