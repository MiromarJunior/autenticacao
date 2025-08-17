package com.padrao.autenticacao.service;

import com.padrao.autenticacao.dto.request.CadastrarUsuarioRequest;
import com.padrao.autenticacao.dto.request.ListarUsuarioRequest;
import com.padrao.autenticacao.dto.response.UsuarioResponse;
import com.padrao.autenticacao.model.Usuario;
import org.springframework.data.domain.Page;

public interface UsuarioService {

    UsuarioResponse salvar(CadastrarUsuarioRequest request);

    Page<UsuarioResponse> listarUsuarioPaginado(ListarUsuarioRequest request);
}
