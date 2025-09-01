package com.padrao.autenticacao.login.controller;

import com.padrao.autenticacao.login.dto.request.LoginRequest;
import com.padrao.autenticacao.usuario.dto.response.LoginResponse;
import com.padrao.autenticacao.login.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping()
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {

        var response = loginService.login(request);
        return ResponseEntity.ok(response);
    }

}
