package com.padrao.autenticacao.service;

import com.padrao.autenticacao.dto.request.LoginRequest;
import com.padrao.autenticacao.dto.response.LoginResponse;

public interface LoginService {

    LoginResponse login(LoginRequest request);
}
