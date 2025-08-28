package com.padrao.autenticacao.controller;

import com.padrao.autenticacao.dto.request.LoginRequest;
import com.padrao.autenticacao.dto.response.LoginResponse;
import com.padrao.autenticacao.security.JwtUtil;
import com.padrao.autenticacao.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/auth")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {

        var response = loginService.login(request);
        return ResponseEntity.ok(response);
    }

}
