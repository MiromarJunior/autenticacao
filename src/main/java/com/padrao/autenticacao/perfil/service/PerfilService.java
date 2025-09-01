package com.padrao.autenticacao.perfil.service;

import com.padrao.autenticacao.perfil.dto.request.CriarPerfilRequest;
import com.padrao.autenticacao.perfil.dto.request.ListarPerfilRequestPaginado;
import com.padrao.autenticacao.perfil.dto.response.PerfilResponse;
import org.springframework.data.domain.Page;

public interface PerfilService  {

    Page<PerfilResponse> listarPaginado(ListarPerfilRequestPaginado request);

    PerfilResponse salvar(CriarPerfilRequest request);
}
