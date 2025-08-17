package com.padrao.autenticacao.dto.request;

import com.padrao.autenticacao.enums.UsuarioPaginadoOrdenacaoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListarUsuarioRequest {

    private String nome;

    private String email;

    private Sort.Direction direction = Sort.Direction.ASC;

    private UsuarioPaginadoOrdenacaoEnum sort = UsuarioPaginadoOrdenacaoEnum.NOME;

    private Integer page = 0;

    private Integer size = 10;
}
