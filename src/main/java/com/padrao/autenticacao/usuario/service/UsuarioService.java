package com.padrao.autenticacao.usuario.service;

import com.padrao.autenticacao.usuario.dto.request.CadastrarUsuarioRequest;
import com.padrao.autenticacao.usuario.dto.request.ListarUsuarioRequest;
import com.padrao.autenticacao.usuario.dto.response.UsuarioResponse;
import org.springframework.data.domain.Page;

public interface UsuarioService {

    UsuarioResponse salvar(CadastrarUsuarioRequest request);

    Page<UsuarioResponse> listarUsuarioPaginado(ListarUsuarioRequest request);
}
