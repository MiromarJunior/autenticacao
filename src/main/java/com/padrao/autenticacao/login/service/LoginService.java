package com.padrao.autenticacao.login.service;

import com.padrao.autenticacao.login.dto.request.LoginRequest;
import com.padrao.autenticacao.usuario.dto.response.LoginResponse;

public interface LoginService {

    LoginResponse login(LoginRequest request);
}
