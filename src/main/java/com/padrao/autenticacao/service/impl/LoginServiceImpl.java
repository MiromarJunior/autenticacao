package com.padrao.autenticacao.service.impl;

import com.padrao.autenticacao.dto.request.LoginRequest;
import com.padrao.autenticacao.dto.response.LoginResponse;
import com.padrao.autenticacao.exception.EntityNotFoundException;
import com.padrao.autenticacao.exception.UnauthorizedException;
import com.padrao.autenticacao.model.Usuario;
import com.padrao.autenticacao.repository.UsuarioRepository;
import com.padrao.autenticacao.security.JwtUtil;
import com.padrao.autenticacao.service.LoginService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    public LoginServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail()).orElseThrow(
                ()-> new EntityNotFoundException("Usuário não encontrado")
        );

        if (!passwordEncoder.matches(request.getSenha(), usuario.getSenha())) {
          throw new UnauthorizedException("Credenciais inválidas");
        }

        return new LoginResponse(JwtUtil.generateToken(usuario));

    }
}
