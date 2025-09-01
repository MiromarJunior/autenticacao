package com.padrao.autenticacao.perfil.dto.request;

import com.padrao.autenticacao.usuario.enums.UsuarioPaginadoOrdenacaoEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class ListarPerfilRequestPaginado {

    private String nome;

    private String descricao;

    private Boolean status;

    private Sort.Direction direction = Sort.Direction.ASC;

    private UsuarioPaginadoOrdenacaoEnum sort = UsuarioPaginadoOrdenacaoEnum.NOME;

    private Integer page = 0;

    private Integer size = 10;
}
