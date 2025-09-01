package com.padrao.autenticacao.perfil.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CriarPerfilRequest {

    @NotBlank(message = "Campo descrição é obrigatório!")
    private String descricao;
}
